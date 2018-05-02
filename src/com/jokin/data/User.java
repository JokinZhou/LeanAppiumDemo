/**
 * 
 */
package com.jokin.data;

/**
 * @author jokin
 *定义一个User对象类型用来存储，用户信息，报告用户id，name  password
 *
 */
public class User {
	private  String id =null;
	private  String name =null;
	private  String password= null;
	
	public User(){
		
	}
	
	public User(String id,String name,String password){
		this.id =id;
		this.name =name;
		this.password =password;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
			

}
