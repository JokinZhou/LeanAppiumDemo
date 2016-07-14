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
 * ��װһЩappium���õĲ����������磺��λ������������һ��������������룬�������������б��ѡ��ѡ��,���أ�android����
 *  ҡһҡ����б ������ʵ��
 *  ����ʹ�� webdriver��sendKeys ����ʵ��
 * ���� ����appium��api��TouchAction���е�LongPress�������÷����ж��ֹ��죻���������ꡢԪ�������
 *��������home�� ���ǵ���AndroidDriver��װ��longPressKeyCode ����
 *�����click
 */
public class AppiumActionUtil {
	/**
	 * 
	 * @param driver 
	 * @param pathType  ��ʾ���Ƕ�λ�ķ�ʽ id��name,Xpath
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
					return arg0.findElement(By.id(locationValue));//ֱ�Ӱ�����ҵ���Ԫ����Ϊ����ֵ,return�����������������ֵ
				}
				
			});
			element=driver.findElementById(locationValue);
		}else if(locationStyle.equals("name")){
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// ��ʽ�ȴ���ȫ�ֵȴ�30s����Ԫ���Ƿ��Ѿ�����
			element=driver.findElementByName(locationValue);
		}else if(locationStyle.equals("Xpath")){
			//��ʾ�ȴ�
			new AndroidDriverWait(driver,AndroidDriverWait.DEFAULT_SLEEP_TIMEOUT)
			.until(new ExpectedCondition<WebElement>() {//until�ĵ���Ӣ����˼Ϊ   ֱ����������Ϊֹ
				public WebElement apply(AndroidDriver d) {
                    return d.findElement(By.xpath(locationValue));
                }
            });
			element=driver.findElementByXPath(locationValue);
		}else if(locationStyle.equals("")){//����λ��ʽΪ��ʱ
			System.out.println("û��д�붨λ��ʽ");
		}
		return element;
	}
	
	/**
	 * �����������Ĭ��text���ҵ���ѡ��ʱ��������յ���յĴ���
	 * ���Ƶ��������element.clear()��û��Ч����; ��Ĭ�ϵ���ʾ�ı�(�ּ��Ƚ�ǳ)
	 * @param driver
	 * @param editText
	 */
	public static void clearEditText(AndroidDriver driver,WebElement editText){
		editText.click();
		driver.pressKeyCode(123);//123������ƶ�����������ұ�
		String content = editText.getText();//����WebElementԪ�ص�getText()������ȡԪ�ص�ֵ
		for(int i =0;i<content.length();i++){
			driver.pressKeyCode(67); //67��delete��
			driver.findElementsByAndroidUIAutomator(content);
		}
		
	}
	/**
	 * ʵ���ϻ�����
	 * @param driver
	 * @param duration  ���������ĳ���ʱ�䣬 ��λΪ����
	 */
	public static void swipeToUp(AndroidDriver<?> driver,int duration){
		//��ȡ��Ļ��͸�     ��Դ���֪  ���ֱ����dimension�������final��Ա�����ʹ�on��getWidth()�䷵�ص�һ����
		int width = driver.manage().window().getSize().width;//�鿴Դ��֪��getSize()�������ص���һ��dimension�����󣬶�����ֻ��һֱ�вεĹ��췽������ˣ�width��height�϶�����ֵ��
		int height = driver.manage().window().getSize().height;
		driver.swipe(width / 2, height * 3/ 4, width /2 , height /4, duration);
	}
	/**
	 * ʵ���»�
	 * @param driver
	 * @param duration ���������ĳ���ʱ�䣬��λΪ����
	 */
	public static void swipeToDown(AndroidDriver driver, int duration){
		//
		 int width = driver.manage().window().getSize().width;
		 int height = driver.manage().window().getSize().height;
		 driver.swipe(width/2, height /4, width /2, height *3/4, duration);
	}
	/**
	 * ʵ����
	 * @param driver
	 * @param duration ���������ĳ���ʱ�䣬��λΪ����
	 */
	public static void swipeToLeft(AndroidDriver driver ,int duration){
		int width = driver.manage().window().getSize().width;
		int height =driver.manage().window().getSize().height;
		driver.swipe(width *3/4, height/2, width/4, height/2, duration);
		
	}
	/**
	 * ʵ���һ�
	 * @param driver
	 * @param duration ���������ĳ���ʱ�䣬��λΪ����
	 */
	public static void swipeToRight(AndroidDriver driver,int duration){
		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;
		driver.swipe(width/4, height/2, width*3/4, height/2, duration);
	}
	/**
	 * ���������ѡ��(���������б�������ѡ�driver��find���ǵ����������Ϊ�����������ˣ�����Ҫ�����ⷽ�����)
	 * �������ԣ�
	 * @param driver
	 * @param locationStyle ��λ��ʽ
	 * @param locationValue  ��λ��ֵ����ѡѡ���ֵ��
	 */
	public static void clickListboxItem(AndroidDriver driver,String locationStyle, final String locationValue){
		//���������б�������ѡ�driver��find���ǵ����������Ϊ�����������ˣ�
		WebElement toElement = findViewTool(driver, locationStyle, locationValue);
		//����һ
		Actions action = new Actions(driver);
		//��������Ӧ��Ԫ������;;;����Ԫ���޷�ֱ�Ӷ�λ ;  �ƶ���Ԫ���ϲ����
		action.moveToElement(toElement).click().build().perform();
		//������  selenium��ץ������webҳ��������б���
		Select select = new Select(toElement);
		select.selectByValue(locationValue);
		/*//ȡ��ѡ��XXXX
		select.deselectByValue("��ѡ������ѡ��");*/
	}
	/**
	 * app���뵽webViewҳ����ת��ģʽ��װ�������ԣ�
	 * @param driver
	 * @param appViewType
	 * @throws InterruptedException
	 */
	public static void switchNativeOrWeb(AndroidDriver<?> driver,String appViewType) {
		//һ��app����ͬʱ�ֶ��context������application����Activity����service����ÿ����һ�������Լ���context��
		//��ȡ���ص�ǰ���е�conext��
		Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); // ���ڷ��ر���app��NATIVE_APP����WEBVIEW��������߶��о��ǻ����App
        }

        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// ����һ��

        if(appViewType != null && appViewType.equals("webview") ){
        	driver.context("WEBVIEW_01");// ��appium�л���webviewģʽ�Ա����webԪ��
        }else{
        	driver.context("NATIVE_APP");
        }
        
	}

}
