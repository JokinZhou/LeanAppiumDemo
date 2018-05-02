/**
 * 
 */
package screenShot;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

/**
 * @author jokin
 *
 */
public class ScreenShot {
	/**
	 * 	//��ͼ����      ��̬�෽��  �������쳣ʱ���ô˷��� �� �Ϳɽ�ͼ������Ϣ
	 * @param driver    ԭ�����ڴ˴�ʹ�õ���TakesScreenshot�ӿڣ���һ���ӿڿ��Լ̳ж���ӿڣ���һ����ֻ�ܼ̳�һ���ࣩ
	 * ��������WebDriver�ӿڼ̳���TakesScreenshot�ӿڣ���appium�ӿ��ּ̳���WebDriver�ӿڣ����Դ˴�����ֱ����AndroidDriver��
	 * @param ScreenName ��ͼ�ļ�����һ����
	 * @throws IOException
	 */
	public static void Screenshot(AndroidDriver<?> driver,String ScreenName) throws IOException{
			//����ʱ���ʽ
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd-ss");
			//����ʱ���
			String dateString = formatter.format(new Date());
			String dir_name=System.getProperty("user.dir")+"\\�쳣ͼƬ";//�����ű���ͼƬ��·��     ����·��/�쳣ͼƬ
			System.out.println("�쳣ͼƬĿ¼"+dir_name);
			//���ڿ��ܴ����쳣Ŀ¼ͼƬ��Ŀ¼��ɾ���Ŀ��ܣ�����������ж�Ŀ¼�Ƿ����
			if (!(new File(dir_name).isDirectory()))
			{//�ж��Ƿ���ڸ�Ŀ¼
				new File(dir_name).mkdir();
			}
			//���÷�����׽����
			File screen = driver.getScreenshotAs(OutputType.FILE);
			//�����ļ���ָ��Ŀ¼
			//ͼƬ����ŵ�·�� Ŀ¼��dir_name +ʱ���+�����׼�+��������+���Բ����������
			System.out.println("�쳣ͼƬ����"+dir_name+"\\"+dateString+ScreenName+".jpg");
			//�����ļ� copy one file into another
			FileUtils.copyFile(screen,new File(dir_name+"\\"+dateString+ScreenName+".jpg"));
					
					
		}


}
