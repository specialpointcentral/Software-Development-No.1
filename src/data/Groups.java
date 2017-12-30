package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Groups implements Serializable{//组织用户
	public ArrayList<User> users;//用户
	public String name;//组织名称
	public int ID;//ID
	
	public Groups() {
		users=new ArrayList<User>();
	}
	public String toString() {
		return name;
	}
}
