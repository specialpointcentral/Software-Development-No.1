package data;

import java.io.Serializable;

public class Activity implements Serializable {// ־Ը���Ϣ
	public String name;// �����
	public float hour;// ־Ըʱ��
	public Group group;// ��֯���֯
	public boolean checked;//�Ƿ����
	Dates date;// �ʱ��
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

class Dates implements Serializable{// ����
	int year;
	int mouth;
	int day;
}