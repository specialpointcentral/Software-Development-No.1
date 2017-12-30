package data;

import java.io.Serializable;

public class Activity implements Serializable {// 志愿活动信息
	public String name;// 活动名字
	public float hour;// 志愿时长
	public Group group;// 组织活动组织
	public boolean checked;//是否审核
	Dates date;// 活动时间
	public Activity(){
		date=new Dates();
	}

	public int getYear() {
		return date.year;
	}

	public int getMonth() {
		return date.mouth;
	}

	public int getDay() {
		return date.day;
	}

	public void setYear(int i) {
		date.year = i;
	}

	public void setMonth(int i) {
		date.mouth = i;
	}

	public void setDay(int i) {
		date.day = i;
	}

	public String toString() {
		return name + "-" + hour;
	}

}

class Dates implements Serializable{// 日期
	int year;
	int mouth;
	int day;
}