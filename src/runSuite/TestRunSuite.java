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
		ts.addTestSuite(MainFunctionTest.class);//���繦�ܲ���������testCase,��testCase����excel������ʽ����ȡ��
		//ִ�в��Լ�
		junit.textui.TestRunner.run(ts);
		//ʹ�ø���ľ�̬�෽��
/*		File data = new File("d:\\UserAndPassword.xml");
		OperationXML.readXML(data);*/
		
/*		File dataExcel = new File("g:\\data.xls");
		List<ActionTestData> testData = ReadAndWriteExcelByJXL.getData(dataExcel);
		for(int i=0;i<3;i++){
			//ִ�����
			System.out.println("caseNO:"+ testData.get(i).getCaseNo());
			System.out.println("action:"+testData.get(i).getAction());
			System.out.println("LocationStyle:"+testData.get(i).getLocationStyle());
			System.out.println("LocationValue:"+testData.get(i).getLocationValue());
			System.out.println("ActionValue:"+testData.get(i).getActionValue());
			//������
			System.out.println("ExpectationStyle:"+testData.get(i).getExpectationStyle());
			System.out.println("expectationLocationStyle:"+testData.get(i).getExpectationLocationStyle());
			System.out.println("expectationLocationValue:"+testData.get(i).getExpectationLocationValue());
			System.out.println("Expectation:"+testData.get(i).getExpectation());
			System.out.println("....................");
			//ʹ�ö�ȡ����excel��������ȥ���ùؼ��ֿ�ܽ��в��ԣ� ����ȡ����ֵ��ֱ����Ϊ������ActionFactory����executeAction ȥִ������
           // ��ִ�еĴ��������MainTest��ȥ�� main���� ���� ֻ���������TestSuite���д���������ʼ����
			ActionFactory.executeAction(driver, testData.get(i).getAction(), testData.get(i).getLocationStyle()
					,testData.get(i).getLocationValue(), testData.get(i).getActionValue());
		}*/
		
		

	}

}
