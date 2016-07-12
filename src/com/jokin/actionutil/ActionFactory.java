/**
 * 
 */
package com.jokin.actionutil;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;

/**
 * @author jokin
 *
 */
public class ActionFactory {
	
	public static void executeAction( AndroidDriver driver ,String action,String locationStyle ,String locationValue,String actionvalue){
		
		
		if(action!=null &&action.equals("input")){
			WebElement editText =AppiumActionUtil.findViewTool(driver, locationStyle, locationValue);
			editText.sendKeys(actionvalue);
		}else if(action!=null &&action.equals("click")){
			WebElement button =AppiumActionUtil.findViewTool(driver, locationStyle, locationValue);
			button.click();
		}else if(action!=null &&action.equals("clearText")){
			WebElement editText =AppiumActionUtil.findViewTool(driver, locationStyle, locationValue);
			AppiumActionUtil.clearEditText(driver, editText);
		}else if(action!=null &&action.equals("longPress")){
			WebElement button =AppiumActionUtil.findViewTool(driver, locationStyle, locationValue);
			TouchAction  ta = new TouchAction(driver);
			ta.longPress(button);
		}else if(action!=null &&action.equals("swipeToLift")){
			AppiumActionUtil.swipeToLeft(driver, 1000);
		}else if(action!=null &&action.equals("swipeToRight")){
			AppiumActionUtil.swipeToRight(driver, 1000);
		}else if(action!=null &&action.equals("swipeToTop")){
			AppiumActionUtil.swipeToUp(driver, 1000);
		}else if(action!=null &&action.equals("swipeToDown")){
			AppiumActionUtil.swipeToDown(driver, 1000);
		}/*else if(action.equals("swipeToTop")){
			
		}else if(action.equals("swipeToTop")){
			
		}*/
	}

}
