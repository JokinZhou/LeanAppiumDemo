/**
 * junit 的测试项目
 * 
 */
package com.jokin.testcase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import jxl.read.biff.BiffException;

import org.apache.commons.collections.functors.ExceptionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.jokin.Init.InitAppium;
import com.jokin.actionutil.ActionFactory;
import com.jokin.actionutil.ExceptioneFactory;
import com.jokin.data.ReadAndWriteExcelByJXL;
import com.jokin.data.ActionTestData;
import com.jokin.data.ResultOfTest;

/**
 * @author jokin
 *
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class MainFunctionTest extends TestCase {

  private AppiumDriver driver;

  @Before
  public void start() throws MalformedURLException {
	  //���÷�װ�õ�InitAppiumȥ��ʼappium�Ĳ���
	  try {
		driver = InitAppium.initIntallAPK("D:\\testAPP.APK");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }

  @Test
  public void testFunction() throws BiffException, IOException {
	  
		File dataExcel = new File("g:\\data.xls");
		//����ReadAndWriteExcelByJXL��������excel�Ľ���
		List<ActionTestData> actionTestData = ReadAndWriteExcelByJXL.getData(dataExcel);
		List<ResultOfTest> resultList  = new ArrayList<ResultOfTest>();
		for(int i=0;i<actionTestData.size();i++){
			//ִ�����
			System.out.println("caseNO:"+ actionTestData.get(i).getCaseNo());
			System.out.println("action:"+actionTestData.get(i).getAction());
			System.out.println("LocationStyle:"+actionTestData.get(i).getLocationStyle());
			System.out.println("LocationValue:"+actionTestData.get(i).getLocationValue());
			System.out.println("ActionValue:"+actionTestData.get(i).getActionValue());
			//������
			System.out.println("ExpectationStyle:"+actionTestData.get(i).getExpectationStyle());
			System.out.println("expectationLocationStyle:"+actionTestData.get(i).getExpectationLocationStyle());
			System.out.println("expectationLocationValue:"+actionTestData.get(i).getExpectationLocationValue());
			System.out.println("Expectation:"+actionTestData.get(i).getExpectation());
			System.out.println("....................");
			//ʹ�ö�ȡ����excel��������ȥ���ùؼ��ֿ�ܽ��в��ԣ� ����ȡ����ֵ��ֱ����Ϊ������ActionFactory����executeAction ȥִ������
			//ִ�ж���
			if(driver!=null){
				ActionFactory.executeAction((AndroidDriver) driver, actionTestData.get(i).getAction(), actionTestData.get(i).getLocationStyle()
						,actionTestData.get(i).getLocationValue(), actionTestData.get(i).getActionValue());
			}else{
				System.out.println("��ʼ��appniumʧ�ܣ�driver��ʼ��ʧ��");
			}
			//ʵ�ʽ����Ԥ�ڽ�����жԱ�
			ResultOfTest resultOfTest = ExceptioneFactory.equalException((AndroidDriver) driver, actionTestData.get(i));
			resultList.add(resultOfTest);
		}
		
		try {
			ReadAndWriteExcelByJXL.writeResult("���Խ��", resultList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���Խ��д�뵽excel���ʱ�����쳣");
		}
  }

  @After
  public void stop() {
    driver.quit();
  }

}
