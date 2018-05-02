/**
 * 
 */
package runSuite;

import java.io.File;
import java.util.List;

import com.jokin.actionutil.ActionFactory;
import com.jokin.data.OperationXML;
import com.jokin.data.ReadAndWriteExcelByJXL;
import com.jokin.testcase.MainFunctionTest;

import junit.framework.TestSuite;

import com.jokin.data.ActionTestData;

/**
 * @author zhouyiqing
 *
 */
public class TestRunSuite {

	/**
	 * 
	 */
	public TestRunSuite() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TestSuite ts = new TestSuite();
		//ts.addTest(Test);
		ts.addTestSuite(MainFunctionTest.class);//假如功能测试用例的testCase,而testCase是以excel表格的形式被读取的
		//执行测试集
		junit.textui.TestRunner.run(ts);
		//使用该类的静态类方法
/*		File data = new File("d:\\UserAndPassword.xml");
		OperationXML.readXML(data);*/
		
/*		File dataExcel = new File("g:\\data.xls");
		List<ActionTestData> testData = ReadAndWriteExcelByJXL.getData(dataExcel);
		for(int i=0;i<3;i++){
			//执行相关
			System.out.println("caseNO:"+ testData.get(i).getCaseNo());
			System.out.println("action:"+testData.get(i).getAction());
			System.out.println("LocationStyle:"+testData.get(i).getLocationStyle());
			System.out.println("LocationValue:"+testData.get(i).getLocationValue());
			System.out.println("ActionValue:"+testData.get(i).getActionValue());
			//结果相关
			System.out.println("ExpectationStyle:"+testData.get(i).getExpectationStyle());
			System.out.println("expectationLocationStyle:"+testData.get(i).getExpectationLocationStyle());
			System.out.println("expectationLocationValue:"+testData.get(i).getExpectationLocationValue());
			System.out.println("Expectation:"+testData.get(i).getExpectation());
			System.out.println("....................");
			//使用读取到的excel表格参数，去调用关键字框架进行测试； 将读取到的值，直接作为参数给ActionFactory里面executeAction 去执行用例
           // 该执行的代码放在了MainTest中去了 main函数 最终 只是用来添加TestSuite进行代码管理如最开始几句
			ActionFactory.executeAction(driver, testData.get(i).getAction(), testData.get(i).getLocationStyle()
					,testData.get(i).getLocationValue(), testData.get(i).getActionValue());
		}*/
		
		

	}

}
