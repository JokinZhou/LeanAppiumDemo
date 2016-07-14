/**
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
	  //调用封装好的InitAppium去初始appium的参数
	  try {
		driver = InitAppium.initIntallAPK("D:\\testAPP.APK");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

  }

  @Test
  public void testFunction() {
	  
		File dataExcel = new File("g:\\data.xls");
		//调用ReadAndWriteExcelByJXL方法进行excel的解析
		List<ActionTestData> actionTestData = ReadAndWriteExcelByJXL.getData(dataExcel);
		List<ResultOfTest> resultList  = new ArrayList<ResultOfTest>();
		for(int i=0;i<actionTestData.size();i++){
			//执行相关
			System.out.println("caseNO:"+ actionTestData.get(i).getCaseNo());
			System.out.println("action:"+actionTestData.get(i).getAction());
			System.out.println("LocationStyle:"+actionTestData.get(i).getLocationStyle());
			System.out.println("LocationValue:"+actionTestData.get(i).getLocationValue());
			System.out.println("ActionValue:"+actionTestData.get(i).getActionValue());
			//结果相关
			System.out.println("ExpectationStyle:"+actionTestData.get(i).getExpectationStyle());
			System.out.println("expectationLocationStyle:"+actionTestData.get(i).getExpectationLocationStyle());
			System.out.println("expectationLocationValue:"+actionTestData.get(i).getExpectationLocationValue());
			System.out.println("Expectation:"+actionTestData.get(i).getExpectation());
			System.out.println("....................");
			//使用读取到的excel表格参数，去调用关键字框架进行测试； 将读取到的值，直接作为参数给ActionFactory里面executeAction 去执行用例
			//执行动作
			if(driver!=null){
				ActionFactory.executeAction((AndroidDriver) driver, actionTestData.get(i).getAction(), actionTestData.get(i).getLocationStyle()
						,actionTestData.get(i).getLocationValue(), actionTestData.get(i).getActionValue());
			}else{
				System.out.println("初始化appnium失败，driver初始化失败");
			}
			//实际结果与预期结果进行对比
			ResultOfTest resultOfTest = ExceptioneFactory.equalException((AndroidDriver) driver, actionTestData.get(i));
			resultList.add(resultOfTest);
		}
		
		try {
			ReadAndWriteExcelByJXL.writeResult("测试结果", resultList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("测试结果写入到excel表格时出现异常");
		}
  }

  @After
  public void stop() {
    driver.quit();
  }

}
