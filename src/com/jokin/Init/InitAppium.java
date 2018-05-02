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
 *�����������ú�ʵ��appium�������ĳ�ʼ���� ��
 */
public class InitAppium {
	/**
	 * ��װAPP�������ó�ʼ��appium  ���س�ʼ�����driver 
	 * @param fileDir   ����APP�Ĵ��Ŀ¼
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
	 * ����װAPP��ֱ�����ó�ʼ��appium  ���س�ʼ�����driver
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
