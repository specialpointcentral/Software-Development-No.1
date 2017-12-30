package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Volenteer implements Serializable {//志愿者信息，存放志愿者信息以及志愿者参加的各类志愿活动
	public String name;//姓名
	public ArrayList<Activity> activities;//志愿活动集合
	public int IDnum;//学号
	public String sex;//性别
	public String state;//志愿者在组织的状态
	
	public Volenteer() {
		activities=new ArrayList<Activity>();
		state="临时";
	}
	
	public String toString() {
		return name+"-"+sex;
	}

}
