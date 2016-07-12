/**
 * 
 */
package com.jokin.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author jokin
 *只解析包括跟节点 应该三级标签； 此处的例子是一个用户信息的数据，并将结果写进了user这个类的对象中
 */
public class OperationXML {
	
	private static Document document;

	/**
	 * 
	 */
	public OperationXML() {
		// TODO Auto-generated constructor stub
	}
	
	public static List<User> readXML(File dataFile ){
		//定义一个List的集合里面存放的数据类型的对象 ，为read的方法的最终返回值
		List<User> userList = new ArrayList();
		
/*	  // 读取XML文件，获取到document对象               方法一
		SAXReader sa = new SAXReader();
		try {
			document = sa.read(dataFile); //此处read()方法有多种构造
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element root = document.getRootElement();//获取根节点
		List<Element> oneChildElements = root.elements();//elements() Returns the elements contained in this element.
		//遍历每个标签，及标签中的属性
		for(int i=0;1<oneChildElements.size();i++){
			Element childElement = oneChildElements.get(i);
			List<Attribute> attributes = childElement.attributes();//获取每个标签的属性集
			for(int j=0;j<attributes.size();j++){
			Attribute attribute =attributes.get(j);
			System.out.println( attribute.getName() + ": " +  attribute.getValue());
			}	
		}*/
		
		//方法二，使用Iterator迭代器的方式来解析xml
		SAXReader sa = new SAXReader();
		try {
			document = sa.read(dataFile);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取根节点
		Element root = document.getRootElement();
		//直接到某一个节点下
		//List<Element> le=document.selectNodes("/Response/Main/Item");
		//获取元素节点的迭代器；此处获取的是根节点的
		Iterator<?>  iterator  =root.elementIterator();
		//while循环获取，节点中每个属性的值
		while(iterator.hasNext()){//循环获得第二级标签和值
			//调用next()方法获取Returns the next element in the iteration.
			Element el= (Element)iterator.next();
			System.out.println(el.getName()+": id="+el.attribute(0).getStringValue());
			Iterator<?> iterator2=el.elementIterator();
			//定义一个User对象类型，用户存放读取到的XML中的文件，最终生成一个list集合作为该方法的返回值
			User user = new User();
			user.setId(el.attribute(0).getStringValue());
			while(iterator2.hasNext()){//循环获取到第三级标签和值
				Element datas= (Element) iterator2.next();
				//System.out.println(datas.getName()+"的值是："+ datas.getStringValue());
				System.out.println(datas.getName()+"的值是："+ datas.getText());
				if("name".equals(datas.getName())){
					user.setName(datas.getText());
				}
				else {
					user.setPassword(datas.getText());
				}
			}
			//将生成的user对象加入到list集合
			userList.add(user);
			
		}
		
		/*System.out.println(userList.get(0).getPassword());//用来检查最后获得的结果对不对
		System.out.println(userList.get(1).getPassword());*/
		return userList;
		
	}
	/**
	 * 
	 * @param file   要将XML文件输入到哪个文件里
	 * @param document  将要输出的XML内容的Document对象
	 */
	//定义一个文件用户输出，读取的到的结果
	public static void writeXML(File file,Document document){
		FileWriter filewriter =null;
		OutputFormat outputFormat = new OutputFormat();//创建的文件会自动缩进格式（xmL格式）
		outputFormat.setEncoding("UTF_8");//设置输出流的编码格式为UTF_8；使其支持中文
		//定义输出字符流文件
		try {
			 filewriter = new FileWriter(file);
			 XMLWriter xmlWriter = new XMLWriter(filewriter,outputFormat);
			xmlWriter.write(document);
			filewriter.close();//虽然Java有自动内存回收机制，但是如果是数据库连接、网络连接、文件操作等，
			//不close是不会被回收的，属于不正确的代码。最好在finally块内做close，因为即使发生了例外，这些代码也能被调用
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("将文档的字符流写入XML文档中有异常");
		}finally {
			System.out.print("将信息输入文件完成");
		}
	}

}
