/**
 * 
 */
package com.jokin.actionutil;

import java.io.IOException;

import org.openqa.selenium.WebElement;

import com.jokin.data.ActionTestData;
import com.jokin.data.ReadAndWriteExcelByJXL;
import com.jokin.data.ResultOfTest;

import screenShot.ScreenShot;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author jokin
 *
 */
public class ExceptioneFactory {
	/**
	 *该方法是用来判断 时间结果和预期结果是否一致的，并把结果写到文本里(部分功能未完成)
	 *使用时 每一行的excel表格一个case 会有一个 axtionTestData 对象  
	 *每一行调用调用一次 ExceptioneFactory类中的equalException得到一个ResultOfTest返回值，最后将所有的结果返回值
	 *加入到list，然后最终写到excel表格中
	 * @param driver
	 * @param caseNo
	 * @param expectationStyle
	 * @param expectationLocationStyle
	 * @param expectationLocationValue
	 * @param expectation
	 * @return 
	 */
	public static ResultOfTest equalException(AndroidDriver driver ,ActionTestData actionTestData){
		//初始化一些变量值
		String sheetName = actionTestData.getSheetName();
		String caseNo = actionTestData.getCaseNo();
		String expectationStyle = actionTestData.getExpectationStyle();
		String expectationLocationStyle = actionTestData.getExpectationLocationStyle();
		String expectationLocationValue = actionTestData.getExpectationLocationValue();
		String expectation = actionTestData.getExpectation();
		
		ResultOfTest rt = new ResultOfTest();
		
		//当获取的判断预期的方式为Activity对象时，以预期的结果值 当前的结果值进行比较
		if(expectationStyle != null && expectationStyle.equals("activity")){
			//定义一个定义去存放结果值，用户
			
			//获取当前的的Activity的值
			String currentActivity = driver.currentActivity();
			if(expectation.equals(currentActivity)){
				//判断该测试通过， 然后把测试结果success 写到文本里， 并记录对应的testNo
				System.out.println(caseNo + "success");
				rt.setSheetName(sheetName);
				rt.setCaseNo(caseNo);
				rt.setResult("success");
			}else {
				//将结果写入到文本(待实现)
				System.out.println(caseNo + "fail");
				rt.setSheetName(sheetName);
				rt.setCaseNo(caseNo);
				rt.setResult("fail");
				try {
					ScreenShot.Screenshot(driver, caseNo);//报错错误截图
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(caseNo + "保存错误截图存在IO异常");
				}
			}
		//当获取的判断预期的方式为text文本时，使用预期结果和	
		}else if(expectationStyle != null && expectationStyle.equals("text")){
			WebElement textView =AppiumActionUtil.findViewTool(driver, expectationLocationStyle, expectationLocationValue);
			String actualText = textView.getText();
			if(actualText !=null &&actualText.equals(expectation)){
				//将结果写入到文本(待实现)
				System.out.println(caseNo + "sucess");
				rt.setSheetName(sheetName);
				rt.setCaseNo(caseNo);
				rt.setResult("success");
			}else {
				//将结果写入到文本(待实现)
				System.out.println(caseNo + "fail");
				rt.setSheetName(sheetName);
				rt.setCaseNo(caseNo);
				rt.setResult("fail");
				try {
					ScreenShot.Screenshot(driver, caseNo);//报错错误截图
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(caseNo + "保存错误截图存在IO异常");
				}
			}
			
		}
		
		return rt;
	}

}
