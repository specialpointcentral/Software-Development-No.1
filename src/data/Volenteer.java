package data;

import java.io.Serializable;
import java.util.ArrayList;

public class Volenteer implements Serializable {//־Ը����Ϣ�����־Ը����Ϣ�Լ�־Ը�߲μӵĸ���־Ը�
	public String name;//����
	public ArrayList<Activity> activities;//־Ը�����
	public int IDnum;//ѧ��
	public String sex;//�Ա�
	public String state;//־Ը������֯��״̬
	
	public Volenteer() {
		activities=new ArrayList<Activity>();
		state="��ʱ";
	}
	
	public String toString() {
		return name+"-"+sex;
	}

}
