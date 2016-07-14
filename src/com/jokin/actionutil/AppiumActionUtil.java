/**
 * 
 */
package com.jokin.actionutil;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.jokin.waitFouction.AndroidDriverWait;
import com.jokin.waitFouction.ExpectedCondition;

/**
 * @author jokin
 * 封装一些appium常用的操作方法，如：定位组件、上下左右滑动、长按、输入，清空输入框，下拉列表框选择选项,返回（android）等
 *  摇一摇、倾斜 方法待实现
 *  输入使用 webdriver的sendKeys 方法实现
 * 长按 调用appium的api中TouchAction类中的LongPress方法；该方法有多种构造；可以用坐标、元素组件等
 *长按键盘home等 则是调用AndroidDriver封装的longPressKeyCode 方法
 *点击用click
 */
public class AppiumActionUtil {
	/**
	 * 
	 * @param driver 
	 * @param pathType  表示的是定位的方式 id，name,Xpath
	 * @param pathValue
	 * @return
	 */
	public static WebElement findViewTool(AndroidDriver driver,String locationStyle, final String locationValue){
		WebElement element =null;
		if(locationStyle.equals("id")){
			AndroidDriverWait aw = new AndroidDriverWait(driver, AndroidDriverWait.DEFAULT_SLEEP_TIMEOUT);
			aw.until(new ExpectedCondition<WebElement>(){

				@Override
				public WebElement apply(AndroidDriver arg0) {
					// TODO Auto-generated method stub
					return arg0.findElement(By.id(locationValue));//直接把这个找到的元素作为返回值,return是跳出这个方法返回值
				}
				
			});
			element=driver.findElementById(locationValue);
		}else if(locationStyle.equals("name")){
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// 隐式等待，全局等待30s不管元素是否已经加载
			element=driver.findElementByName(locationValue);
		}else if(locationStyle.equals("Xpath")){
			//显示等待
			new AndroidDriverWait(driver,AndroidDriverWait.DEFAULT_SLEEP_TIMEOUT)
			.until(new ExpectedCondition<WebElement>() {//until的单词英文意思为   直到。。。。为止
				public WebElement apply(AndroidDriver d) {
                    return d.findElement(By.xpath(locationValue));
                }
            });
			element=driver.findElementByXPath(locationValue);
		}else if(locationStyle.equals("")){//当定位方式为空时
			System.out.println("没有写入定位方式");
		}
		return element;
	}
	
	/**
	 * 关于输入框有默认text，且当你选中时，不会清空的清空的处理。
	 * 类似的情况，用element.clear()是没有效果的; 即默认的提示文本(字迹比较浅)
	 * @param driver
	 * @param editText
	 */
	public static void clearEditText(AndroidDriver driver,WebElement editText){
		editText.click();
		driver.pressKeyCode(123);//123：光标移动到输入框最右边
		String content = editText.getText();//调用WebElement元素的getText()方法获取元素的值
		for(int i =0;i<content.length();i++){
			driver.pressKeyCode(67); //67：delete键
			driver.findElementsByAndroidUIAutomator(content);
		}
		
	}
	/**
	 * 实现上滑操作
	 * @param driver
	 * @param duration  滑动操作的持续时间， 单位为毫秒
	 */
	public static void swipeToUp(AndroidDriver<?> driver,int duration){
		//获取屏幕宽和高     由源码可知  最后直接用dimension对象调用final成员变量和带on个getWidth()其返回的一样的
		int width = driver.manage().window().getSize().width;//查看源码知道getSize()方法返回的是一个dimension，对象，而该类只有一直有参的构造方法，因此，width和height肯定是有值的
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height * 3/ 4, width /2 , height /4, duration);
	}
	/**
	 * 实现下滑
	 * @param driver
	 * @param duration 滑动操作的持续时间，单位为毫秒
	 */
	public static void swipeToDown(AndroidDriver driver, int duration){
		//
		 int width = driver.manage().window().getSize().width;
		 int height = driver.manage().window().getSize().height;
		 driver.swipe(width/2, height /4, width /2, height *3/4, duration);
	}
	/**
	 * 实现左滑
	 * @param driver
	 * @param duration 滑动操作的持续时间，单位为毫秒
	 */
	public static void swipeToLeft(AndroidDriver driver ,int duration){
		int width = driver.manage().window().getSize().width;
		int height =driver.manage().window().getSize().height;
		driver.swipe(width *3/4, height/2, width/4, height/2, duration);
		
	}
	/**
	 * 实现右滑
	 * @param driver
	 * @param duration 滑动操作的持续时间，单位为毫秒
	 */
	public static void swipeToRight(AndroidDriver driver,int duration){
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width/4, height/2, width*3/4, height/2, duration);
	}
	/**
	 * 点击下拉框选项(由于下拉列表这样的选项，driver能find但是点击不到（因为看不见隐藏了）所以要用特殊方法点击)
	 * （待调试）
	 * @param driver
	 * @param locationStyle 定位方式
	 * @param locationValue  定位的值（待选选项的值）
	 */
	public static void clickListboxItem(AndroidDriver driver,String locationStyle, final String locationValue){
		//由于下拉列表这样的选项，driver能find但是点击不到（因为看不见隐藏了）
		WebElement toElement = findViewTool(driver, locationStyle, locationValue);
		//方法一
		Actions action = new Actions(driver);
		//滑动到对应的元素上面;;;隐藏元素无法直接定位 ;  移动到元素上并点击
		action.moveToElement(toElement).click().build().perform();
		//方法二  selenium是抓门用于web页面的下拉列表框的
		Select select = new Select(toElement);
		select.selectByValue(locationValue);
		/*//取消选择XXXX
		select.deselectByValue("待选的下拉选项");*/
	}
	/**
	 * app进入到webView页面是转换模式封装（待调试）
	 * @param driver
	 * @param appViewType
	 * @throws InterruptedException
	 */
	public static void switchNativeOrWeb(AndroidDriver<?> driver,String appViewType) {
		//一个app可以同时又多个context；比如application对象、Activity对象、service对象每创建一个都有自己的context；
		//获取返回当前所有的conext，
		Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); // 用于返回被测app是NATIVE_APP还是WEBVIEW，如果两者都有就是混合型App
        }

        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 等它一会

        if(appViewType != null && appViewType.equals("webview") ){
        	driver.context("WEBVIEW_01");// 让appium切换到webview模式以便查找web元素
        }else{
        	driver.context("NATIVE_APP");
        }
        
	}

}
