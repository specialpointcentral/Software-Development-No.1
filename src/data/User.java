package data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class User implements Serializable{//用户信息
	String userName;//用户名
	private String password;//密码
	
	public User() {
		
	}
	public User(String str) {
		if(str.equals("Test")) {
			userName="admin";
			password="admin";
		}
	}
	
	
	
	public String getPassword() {
		return password;
	}
	public String getUser() {
		return userName;
	}
	public void setUser(String userName) {
		this.userName=userName;
	}
	public void setPsw(String password) {
		this.password=password;
	}
	public String toString() {
		return userName;
	}
	
}
