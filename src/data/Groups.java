package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Groups implements Serializable{//��֯�û�
	public ArrayList<User> users;//�û�
	public String name;//��֯����
	public int ID;//ID
	
	public Groups() {
		users=new ArrayList<User>();
	}
	public String toString() {
		return name;
	}
}
