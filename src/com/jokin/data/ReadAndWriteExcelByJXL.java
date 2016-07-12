/**
 * 
 */
package com.jokin.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * @author jokin
 * ������ȡ����д��excel����е�����
 *
 */
public class ReadAndWriteExcelByJXL {

	/**
	 * 
	 */
	public ReadAndWriteExcelByJXL(){
		
	}
	/**
	 * Ŀǰֻʵ���˶�ȡ��һ��sheet�� �����Ĵ�ʵ��
	 * ����һ����̬�෽����ʵ�ִ�excel����ж�ȡ��(��̬�෽��ֻ�ܵ�����ľ�̬��Ա����)
	 * ����Ԫ��Ϊ��ʱ��ʹ��getContents()��ȡ���Ľ����""
	 */
	public static List<ActionTestData> getData(File dataExcel) {
		// TODO Auto-generated constructor stub
		//File dataExcel = new File("g:\\data");
		Sheet s =null;
		String sheetName = null;
		Workbook  wb = null;
		try {
			wb =Workbook.getWorkbook(dataExcel);
			s = wb.getSheet(0);//��ȡ��һ�����
			//��ȡsheet������
			sheetName = s.getName();
		} catch (BiffException | IndexOutOfBoundsException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��ȡ��excel�ĵ�һ����������ݵ�����
		int length = s.getRows();//getRowHeight(int)�������ǻ�ȡ�иߣ� ��getRows()�����ǻ�ȡ����
		int realLength=0;
		while(!s.getCell(0, realLength).getContents().equals("") && s. getCell(0, realLength).getContents()!= null ){//��A.equals(B)��ʾ�����
			//ʹ��whileȥɸѡ���ǿյ����� ; ��Ϊadd�������Ǹ���lenth�ģ�����ʵ���ϻ���Ҫ����length���ֵ
			
			realLength++;//�����ڼӸ��жϣ���real���ܴ���length
		}
			//ע��Ҫ��while �������     ��������ѭ������ ����while ����ͻ�����ѭ����
			
		
		//����Ҫ��ŵ�����Ϊ �������Դ���һ��list�б�
		List<ActionTestData> appData = new ArrayList<ActionTestData>();
		ActionTestData actionTestData = null;//new TestData();
		for(int i=1;i<realLength;i++){//�ӵڶ��п�ʼ��ȡ���ݣ���Ϊ��һ���Ǳ���    ��  length
			actionTestData = new ActionTestData();
			actionTestData.setSheetName(sheetName);
			for(int j=0;j<s.getColumns();j++){//getCell(int ,int) ����������ǰ�� ����
				//�ƺ�ֻ�ڻ�ȡ�е�ʱ��������ڻ�ȡ�е�ʱ��û�ж���
					if(j==0){//�������Ǹ� switch(���ʽ){case �������ʽ1: }
						actionTestData.setCaseNo(s.getCell(j, i).getContents());//����һ�еĲ������Ƶ�data������
					}else if(j==1){
						/*String a =s.getCell(j, i).getContents();*/
						actionTestData.setAction(s.getCell(j, i).getContents());
					}else if(j==2){
						actionTestData.setLocationStyle(s.getCell(j, i).getContents());
					}else if(j==3){
						actionTestData.setLocationValue(s.getCell(j, i).getContents());
					}else if(j==4){
						actionTestData.setActionValue((s.getCell(j, i).getContents()));
					}else if(j==5){
						actionTestData.setExpectationStyle(s.getCell(j, i).getContents());
					}else if(j==6){
						actionTestData.setExpectationLocationStyle(s.getCell(j, i).getContents());
					}else if(j==7){
						actionTestData.setExpectationLocationValue(s.getCell(j, i).getContents());
					}else if(j==8){
						actionTestData.setExpectation(s.getCell(j, i).getContents());
					}
				}
			
			appData.add(actionTestData);//ÿһ������һ��TestData���ݶ���
		}
		if(wb!=null){
			wb.close();//��ȡ�꣬�ر�workbook����������
		}
		
			
		return appData;

	}
	/**
	 * �շ������������Խ����ŵ�excel����(�ڽ�������ʱ����������ɫ��컹��ʵ��)
	 * @param testResult
	 * @throws IOException 
	 */
	public static void writeResult(String testResultFileName,List<ResultOfTest> resultOfTest) throws IOException{
		
		OutputStream os = null;
		WritableWorkbook wwb = null;
		
		//����ʱ���ʽ
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd-ss");
		//����ʱ���
		String dateString = formatter.format(new Date());
		//��ȡ�����Խ��������
		int lenghtOfResult = resultOfTest.size();
		//ƴ���ļ�Ŀ¼ Ϊ    \�û�\testResultFileName20160612.xls ���ļ�·����ʽ
		File testResultFile = new File(System.getProperty("user.dir")+"\\"+testResultFileName + dateString + ".xls");
		if (testResultFile !=null && testResultFile.exists()){
			//����һ������� ,��� OS������ ���ֻ�������ͨ��TXT�ĵ��� ֱ��ʹ��OS������
			os = new FileOutputStream(testResultFile);
			/*os.write("�������".getBytes());*/
			 //��ʼ��һ����д��Workbook����
			/*wwb = Workbook.createWorkbook(testResultFile);*/	
		}else if(!testResultFile.exists()){
			//���ļ�������ʱ���ȴ������ļ�
			testResultFile.createNewFile();
			os = new FileOutputStream(testResultFile);
			//��д�������Ĺ��췽ʽ�м����� ���Գ���ʹ�ò���ΪFile��ʽ������
		}
		wwb = Workbook.createWorkbook(os);
		//������һ��д��sheet�� WritableSheet
		WritableSheet wsheet = wwb.createSheet("testResult", 0);//��һ��������sheet�����ڶ��������� ��ʾ�ù���ҳ��excel�д�����һҳ
		for(int i=0;i<lenghtOfResult;i++){
			ResultOfTest rot = resultOfTest.get(i);
			//�����ı��൥Ԫ��
			Label sheetNameColumn = new Label(0, i, rot.getSheetName());//��һ��������ʾ�У� �ڶ�����ʾ�У���������cell������
			Label caseNoColumn = new Label(1, i, rot.getCaseNo());
			Label resultColumn = new Label(2, i, rot.getResult());
			
			try {
				wsheet.addCell(sheetNameColumn);
				wsheet.addCell(caseNoColumn);
				wsheet.addCell(resultColumn);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("���Խ��д�뵽excel���ʱ�����쳣");
			}
		}
	}

}
