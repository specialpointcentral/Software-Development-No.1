package user.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import data.*;

public class Data {
	// in this section will define all global set
	// user set which to login
	public static String groupName;// ��֯��Ϣ
	public static int groupID;// ��֯ID
	public static String loginUser;// ��½��ȥ���û�
	// user collection
	public static ArrayList<Groups> groups;
	// group
	public static Group group;
	// ready?using to identify reading is OK
	public static boolean Userready;
	public static boolean Groupready;
	
	//Need Save or Save is OK?
	public static boolean saveGroup;
	public static boolean saveUser;

	public Data() {
		// TODO Auto-generated constructor stub
		// set null
		groupName = null;
		groupID = -1;
		loginUser = null;
		groups = null;
		group = null;
		Userready = false;
		Groupready = false;
		saveGroup=false;
		saveUser=false;
	}

	/**
	 * ���ļ����ö��룬�õ�User
	 * 
	 * @param file
	 *            �ļ�·��
	 */
	public static void getGroupUser(String file) {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			groups = (ArrayList<Groups>) input.readObject();

			input.close();
			Userready = true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.print("Read setting file ERROR!");
			JOptionPane.showMessageDialog(null, "Read Setting File ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
			System.exit(0);

		}
	}

	/**
	 * ���ļ����ö��룬�õ�Groups
	 * 
	 * @param file
	 *            �ļ�·��
	 */
	public static void getGroup(String file) {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			group = (Group) input.readObject();

			input.close();
			Groupready = true;
		} catch (FileNotFoundException e1) {
			// TODO: handle exception
			e1.printStackTrace();
			// file not find then we will create it
			// also help to first load
			Data.group=new Group();
			Data.group.ID=Data.groupID;
			Data.group.name=Data.groupName;
			writeFile(file);
			Groupready=true;
			JOptionPane.showMessageDialog(null, "�ƺ�û���ҵ�־Ը����Ϣ�ļ�","ERROR",JOptionPane.ERROR_MESSAGE);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.err.print("Read setting file ERROR!");
			JOptionPane.showMessageDialog(null, "Read Setting File ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * ����Ϣд���ļ������е���Ϣ����һ����д��
	 * 
	 * @param file
	 *            �ļ�Ŀ¼
	 */
	public static void writeFile(String file) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(group);//����־Ը�ߵȵ���Ϣ

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
