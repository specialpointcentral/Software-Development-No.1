package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable{//��֯�����ڴ��־Ը����Ϣ�Լ���֯����Ϣ
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
