/**
 * 
 */
package com.jokin.data;

/**
 * @author jokin
 *创建一个测试数据类，用来存放测试数据
 *
 */
public class ActionTestData {
	private String sheetName;//sheet的名字
	private String caseNo =null;//用例标号
	private String action =null;//操作动作
	private String locationStyle=null;//定位方式
	private String locationValue=null;//定位值
	private String actionValue=null;//操作值
	private String expectationStyle=null;//预期的判断方式(如：文字text判断、  Acitvity判断等)
	private String expectationLocationStyle=null;
	private String expectationLocationValue=null;
	private String expectation=null;//预期结果
	
	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}
	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	/**
	 * @return the caseNo
	 */
	public String getCaseNo() {
		return caseNo;
	}
	/**
	 * @param caseNo the caseNo to set
	 */
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the locationStyle
	 */
	public String getLocationStyle() {
		return locationStyle;
	}
	/**
	 * @param locationStyle the locationStyle to set
	 */
	public void setLocationStyle(String locationStyle) {
		this.locationStyle = locationStyle;
	}
	/**
	 * @return the locationValue
	 */
	public String getLocationValue() {
		return locationValue;
	}
	/**
	 * @param locationValue the locationValue to set
	 */
	public void setLocationValue(String locationValue) {
		this.locationValue = locationValue;
	}
	/**
	 * @return the actionValue
	 */
	public String getActionValue() {
		return actionValue;
	}
	/**
	 * @param actionvalue the actionValue to set
	 */
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}
	/**
	 * @return the expectationStyle
	 */
	public String getExpectationStyle() {
		return expectationStyle;
	}
	/**
	 * @param expectationStyle the expectationStyle to set
	 */
	public void setExpectationStyle(String expectationStyle) {
		this.expectationStyle = expectationStyle;
	}
	/**
	 * @return the expectationLocationStyle
	 */
	public String getExpectationLocationStyle() {
		return expectationLocationStyle;
	}
	/**
	 * @param expectationLocationStyle the expectationLocationStyle to set
	 */
	public void setExpectationLocationStyle(String expectationLocationStyle) {
		this.expectationLocationStyle = expectationLocationStyle;
	}
	/**
	 * @return the expectationLocationValue
	 */
	public String getExpectationLocationValue() {
		return expectationLocationValue;
	}
	/**
	 * @param expectationLocationValue the expectationLocationValue to set
	 */
	public void setExpectationLocationValue(String expectationLocationValue) {
		this.expectationLocationValue = expectationLocationValue;
	}
	/**
	 * @return the expectation
	 */
	public String getExpectation() {
		return expectation;
	}
	/**
	 * @param expectation the expectation to set
	 */
	public void setExpectation(String expectation) {
		this.expectation = expectation;
	}
	
	

}
