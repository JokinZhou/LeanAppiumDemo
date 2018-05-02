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
 * 解析读取或者写入excel表格中的数据
 *
 */
public class ReadAndWriteExcelByJXL {

	/**
	 * 
	 */
	public ReadAndWriteExcelByJXL(){
		
	}
	/**
	 * 目前是直接把整个excel表格中的所有sheet中的数据都读取然后封装到一个集合里
	 * 创建一个静态类方法，实现从excel表格中读取数(静态类方法只能调该类的静态成员变量)
	 * 当单元格为空时，使用getContents()获取到的结果是""
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public static List<ActionTestData> getData(File dataExcel) throws BiffException, IOException {
		// TODO Auto-generated constructor stub
		//File dataExcel = new File("g:\\data");
		Sheet s =null;
		String sheetName = null;
		List<ActionTestData> appData=null;//用完要存放的数据为 对象，所以创建一个list列表ss
		Workbook  wb = null;
			wb =Workbook.getWorkbook(dataExcel);
			//获得改excel所有的sheet
			Sheet[] sheets =wb.getSheets();
			int sheetnumber= wb.getNumberOfSheets();
			//for-each方法获取所有的sheet中的数据
			for(Sheet  sheetObj:sheets ){
				
				sheetName = sheetObj.getName();
				//获取该excel的第一个表格中数据的行数
				int length = s.getRows();//getRowHeight(int)方法的是获取行高； 而getRows()方法是获取行数
				int realLength=0;
				while(!s.getCell(0, realLength).getContents().equals("") && s. getCell(0, realLength).getContents()!= null ){//！A.equals(B)表示不相等
					//使用while去筛选不是空的行列 ; 因为add的数量是根据lenth的，所以实际上还是要限制length这个值
					realLength++;//可以在加个判断，让real不能大于length
				}
				//注意要在while 的语句中     加入跳出循环条件 否则while 自身就会无限循环了
				appData = new ArrayList<ActionTestData>();
				ActionTestData actionTestData = null;//new TestData();
				for(int i=1;i<realLength;i++){
					//从第二行开始获取数据，因为第一行是标题    行  length
					actionTestData = new ActionTestData();
					actionTestData.setSheetName(sheetName);
					for(int j=0;j<s.getColumns();j++){//getCell(int ,int) 两个参数是前列 后行
						//似乎只在获取行的时候多余了在获取列的时候并没有多余
							if(j==0){//可以用那个 switch(表达式){case 常量表达式1: }
								actionTestData.setCaseNo(s.getCell(j, i).getContents());//将第一列的参数复制到data数组里
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
					
					appData.add(actionTestData);//每一行新增一个TestData数据对象
				}
			}
		if(wb!=null){
			wb.close();//读取完，关闭workbook工作簿对象
		}
		
		return appData;

	}
	/**
	 * 刚方法用来将测试结果存放到excel表格里，(在结果错误的时候让字体颜色变红还待实现)
	 * @param testResult
	 * @throws IOException 
	 */
	public static void writeResult(String testResultFileName,List<ResultOfTest> resultOfTest) throws IOException{
		
		OutputStream os = null;
		WritableWorkbook wwb = null;
		
		//设置时间格式
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd-ss");
		//生成时间戳
		String dateString = formatter.format(new Date());
		//获取到测试结果的总数
		int lenghtOfResult = resultOfTest.size();
		//拼接文件目录 为    \用户\testResultFileName20160612.xls 的文件路径形式
		File testResultFile = new File(System.getProperty("user.dir")+"\\"+testResultFileName + dateString + ".xls");
		if (testResultFile !=null && testResultFile.exists()){
			//定义一个输出流 ,获得 OS流对象， 如果只输出到普通的TXT文档， 直接使用OS流对象
			os = new FileOutputStream(testResultFile);
			/*os.write("输出内容".getBytes());*/
			 //初始化一个可写的Workbook对象
			/*wwb = Workbook.createWorkbook(testResultFile);*/	
		}else if(!testResultFile.exists()){
			//当文件不存在时，先创建该文件
			testResultFile.createNewFile();
			os = new FileOutputStream(testResultFile);
			//可写工作簿的构造方式有几个， 可以尝试使用参数为File格式的试试
		}
		wwb = Workbook.createWorkbook(os);
		//创建第一可写的sheet即 WritableSheet
		WritableSheet wsheet = wwb.createSheet("testResult", 0);//第一个参数是sheet名，第二个参数是 表示该工作页在excel中处于哪一页
		for(int i=0;i<lenghtOfResult;i++){
			ResultOfTest rot = resultOfTest.get(i);
			//创建文本类单元格
			Label sheetNameColumn = new Label(0, i, rot.getSheetName());//第一个参数表示列， 第二个表示行，第三个是cell的内容
			Label caseNoColumn = new Label(1, i, rot.getCaseNo());
			Label resultColumn = new Label(2, i, rot.getResult());
			
			try {
				wsheet.addCell(sheetNameColumn);
				wsheet.addCell(caseNoColumn);
				wsheet.addCell(resultColumn);
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("测试结果写入到excel表格时出现异常");
			}
		}
	}

}
