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
	 *�÷����������ж� ʱ������Ԥ�ڽ���Ƿ�һ�µģ����ѽ��д���ı���(���ֹ���δ���)
	 *ʹ��ʱ ÿһ�е�excel���һ��case ����һ�� axtionTestData ����  
	 *ÿһ�е��õ���һ�� ExceptioneFactory���е�equalException�õ�һ��ResultOfTest����ֵ��������еĽ������ֵ
	 *���뵽list��Ȼ������д��excel�����
	 * @param driver
	 * @param caseNo
	 * @param expectationStyle
	 * @param expectationLocationStyle
	 * @param expectationLocationValue
	 * @param expectation
	 * @return 
	 */
	public static ResultOfTest equalException(AndroidDriver driver ,ActionTestData actionTestData){
		//��ʼ��һЩ����ֵ
		String sheetName = actionTestData.getSheetName();
		String caseNo = actionTestData.getCaseNo();
		String expectationStyle = actionTestData.getExpectationStyle();
		String expectationLocationStyle = actionTestData.getExpectationLocationStyle();
		String expectationLocationValue = actionTestData.getExpectationLocationValue();
		String expectation = actionTestData.getExpectation();
		
		ResultOfTest rt = new ResultOfTest();
		
		//����ȡ���ж�Ԥ�ڵķ�ʽΪActivity����ʱ����Ԥ�ڵĽ��ֵ ��ǰ�Ľ��ֵ���бȽ�
		if(expectationStyle != null && expectationStyle.equals("activity")){
			//����һ������ȥ��Ž��ֵ���û�
			
			//��ȡ��ǰ�ĵ�Activity��ֵ
			String currentActivity = driver.currentActivity();
			if(expectation.equals(currentActivity)){
				//�жϸò���ͨ���� Ȼ��Ѳ��Խ��success д���ı�� ����¼��Ӧ��testNo
				System.out.println(caseNo + "success");
				rt.setSheetName(sheetName);
				rt.setCaseNo(caseNo);
				rt.setResult("success");
			}else {
				//�����д�뵽�ı�(��ʵ��)
				System.out.println(caseNo + "fail");
				rt.setSheetName(sheetName);
				rt.setCaseNo(caseNo);
				rt.setResult("fail");
				try {
					ScreenShot.Screenshot(driver, caseNo);//��������ͼ
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(caseNo + "��������ͼ����IO�쳣");
				}
			}
		//����ȡ���ж�Ԥ�ڵķ�ʽΪtext�ı�ʱ��ʹ��Ԥ�ڽ����	
		}else if(expectationStyle != null && expectationStyle.equals("text")){
			WebElement textView =AppiumActionUtil.findViewTool(driver, expectationLocationStyle, expectationLocationValue);
			String actualText = textView.getText();
			if(actualText !=null &&actualText.equals(expectation)){
				//�����д�뵽�ı�(��ʵ��)
				System.out.println(caseNo + "sucess");
				rt.setSheetName(sheetName);
				rt.setCaseNo(caseNo);
				rt.setResult("success");
			}else {
				//�����д�뵽�ı�(��ʵ��)
				System.out.println(caseNo + "fail");
				rt.setSheetName(sheetName);
				rt.setCaseNo(caseNo);
				rt.setResult("fail");
				try {
					ScreenShot.Screenshot(driver, caseNo);//��������ͼ
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(caseNo + "��������ͼ����IO�쳣");
				}
			}
			
		}
		
		return rt;
	}

}
