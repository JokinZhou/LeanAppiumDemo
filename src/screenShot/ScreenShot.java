/**
 * 
 */
package screenShot;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

/**
 * @author jokin
 *
 */
public class ScreenShot {
	/**
	 * 	//截图操作      静态类方法  当出现异常时调用此方法 ； 就可截图保存信息
	 * @param driver    原方法在此处使用的是TakesScreenshot接口；（一个接口可以继承多个接口；但一个类只能继承一个类）
	 * 但是由于WebDriver接口继承了TakesScreenshot接口；而appium接口又继承了WebDriver接口；所以此处可以直接用AndroidDriver类
	 * @param ScreenName 截图文件名的一部分
	 * @throws IOException
	 */
	public static void Screenshot(AndroidDriver<?> driver,String ScreenName) throws IOException{
			//设置时间格式
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd-ss");
			//生成时间戳
			String dateString = formatter.format(new Date());
			String dir_name=System.getProperty("user.dir")+"\\异常图片";//定义存放被截图片的路径     工程路径/异常图片
			System.out.println("异常图片目录"+dir_name);
			//由于可能存在异常目录图片的目录被删除的可能，所以这边先判断目录是否存在
			if (!(new File(dir_name).isDirectory()))
			{//判断是否存在该目录
				new File(dir_name).mkdir();
			}
			//调用方法捕捉画面
			File screen = driver.getScreenshotAs(OutputType.FILE);
			//复制文件到指定目录
			//图片最后存放的路径 目录：dir_name +时间戳+测试套件+测试用例+测试步骤组合生成
			System.out.println("异常图片名称"+dir_name+"\\"+dateString+ScreenName+".jpg");
			//复制文件 copy one file into another
			FileUtils.copyFile(screen,new File(dir_name+"\\"+dateString+ScreenName+".jpg"));
					
					
		}


}
