package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable{//组织，用于存放志愿者信息以及组织的信息
	public String name;
	public int ID;
	public ArrayList<Volenteer> vol;
	
	public Group() {
		vol=new ArrayList<Volenteer>();
	}
	
	public String toString() {
		return name+"-"+ID;
	}
	

}
