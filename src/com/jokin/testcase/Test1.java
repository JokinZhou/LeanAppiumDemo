/**
 * 
 */
package com.jokin.testcase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author jokin
 *
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class Test1 extends TestCase {//继承TestCase 是为了能使用哪些判定类

  private AppiumDriver driver;

  @Before
  public void start() throws MalformedURLException {
    // ʹ��phonegap�����hybrid app·��
    String apppath = "D:\\Work\\codes\\hands\\hands-hello-phonegap\\platforms\\android\\bin\\HelloWorld.apk";

    // ��ʼ��AppniumDriver
    DesiredCapabilities capabilities = new DesiredCapabilities();
    //if no need install don't add this
    capabilities.setCapability("app", apppath);// ����app apk����λ��(������Ҫ��װ�������ʱ���˾�����)
    //tongyong ,wulushifou anzhuang dou yao peizhi
    capabilities.setCapability("deviceName", "NX403A");// �������android�豸
    capabilities.setCapability("platformVersion", "4.2.2");// ���android�汾Ϊ4.2.2
    capabilities.setCapability("platformName", "Android");// ���Ϊandroidϵͳ  
    capabilities.setCapability("appPackage", "com.example.hello");// ����app��Activity�����ڰ�
    capabilities.setCapability("appActivity", ".HelloWorld");// ����app��Activity��
    capabilities.setCapability("automationName", "selendroid");// ��Ϊ���Ϊ4.2�汾������ʹ��selendroid

    // http://127.0.0.1:4723/wd/hub��ַ����AppiumServer�ĵ�ַ
    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),//AppiumDriver����ɳ����������Բ���ֱ��ʵ����
        capabilities);
  }

  @Test
  public void testtest() {
    try {
      Set<String> contextNames = driver.getContextHandles();//����APPium���е�getContextHandles()����
      for (String contextName : contextNames) {
        System.out.println(contextName); // ���ڷ��ر���app��NATIVE_APP����WEBVIEW��������߶��о��ǻ����App
      }

      /*Thread.sleep(5000);// ����һ��  ��������Ҫ�����ַ�����ͣ��Ϊ������ɽ���������
*/      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// ��ʽ�ȴ���ȫ�ֵȴ�30s����Ԫ���Ƿ��Ѿ�����

      driver.context("WEBVIEW_0");// ��appium�л���webviewģʽ�Ա����webԪ��
      WebElement text_baidusearch = driver.findElement(By.id("word"));
      text_baidusearch.click();// ����ٶȵ����������webԪ�أ�
      
      /*
       * appium��֧���������� �ο���robotium����js��ʽΪԪ��ֱ������value������
       * ����Selenium��Webdriverִ��js����ʵ����������
       */
      JavascriptExecutor jse = driver;
      jse.executeScript("document.getElementById('word').value='���뷨'");

      Thread.sleep(10000);// ��һ��۲���Ч��
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @After
  public void stop() {
    driver.quit();
  }

}
