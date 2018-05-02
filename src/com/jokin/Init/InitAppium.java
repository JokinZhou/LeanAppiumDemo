/**
 * 
 */
package com.jokin.Init;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author jokin
 *此类用来配置和实现appium的启动的初始化工 ；
 */
public class InitAppium {
	/**
	 * 安装APP，并配置初始化appium  返回初始化后的driver 
	 * @param fileDir   待测APP的存放目录
	 * @return
	 * @throws Exception 
	 */
	public static AndroidDriver initIntallAPK(String fileDir) throws Exception{
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("deviceName", "xiaomi");
		capabilities.setCapability("platformName", "Android");
	    capabilities.setCapability("platformVersion", "6.0");
	   //if no need install don't add this
	    capabilities.setCapability("app", fileDir);
	    //support Chinese 
	    capabilities.setCapability("unicodeKeyboard" ,"True");
	    capabilities.setCapability("resetKeyboard", "True");
	    //no need sign
	    capabilities.setCapability("noSign", "True");
	    capabilities.setCapability("appActivity", ".ui.activity.GuideActivity");
	    capabilities.setCapability("appPackage", "com.zhihu.android");
	    
		return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
	}
	/**
	 * 不安装APP，直接配置初始化appium  返回初始化后的driver
	 * @return
	 * @throws Exception
	 */
	public static AndroidDriver initNotIntallAPK() throws Exception{
		AndroidDriver driver;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("devicesName", "xiaomi");
		capabilities.setCapability("platformName", "android");
		capabilities.setCapability("platformVersion", "6.0");
		//supprot Chinese
		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "Ture");
		//no need sign
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability("appActivity", ".ui.activity.GuideActivity");
		capabilities.setCapability("appPackage", "com.jokin.android");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		return driver;
		
	}
	

}
