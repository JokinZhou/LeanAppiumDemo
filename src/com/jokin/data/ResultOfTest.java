/**
 * 
 */
package com.jokin.data;

/**
 * @author zhouyiqing
 * 封装预期结果和实际结果比对后的，最终结果：内涵两个成员变量，caseNO 和result
 *
 */
public class ResultOfTest {
	private String sheetName;
	private String caseNo;
	private String result;
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
	
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	

}
