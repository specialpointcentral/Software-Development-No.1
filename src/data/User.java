package data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class User implements Serializable{//�û���Ϣ
	String userName;//�û���
	private String password;//����
	
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
