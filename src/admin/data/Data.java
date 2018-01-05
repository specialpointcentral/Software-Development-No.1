package admin.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import data.*;

public class Data {
	//set login user
	public static Collection<User> loginUser;
	//set other group user
	public static Collection<Groups> groups;
	//set group collection
	//this is save the group which is read
	public static Collection<Group> readGroup;
	//group recent read
	public static Group recentGroup;
	//group witch has read
	public static ArrayList<Integer> groupReadID;
	//user info
	public static String user;
	//ready?
	public static boolean Userready;//user
	public static boolean Groupsready;//all group info
	public static boolean recentReadReady;//recent reading is ready
	
	//save?
	public static boolean GroupSaveOK;
	public static boolean GroupsSaveOK;
	public static boolean UsersSaveOK;
	
	public Data() {
		loginUser=new ArrayList<User>();
		groups=new ArrayList<Groups>();
		readGroup=new ArrayList<Group>();
		groupReadID=new ArrayList<Integer>();
		user=null;
		Userready=false;
		recentGroup=null;
		GroupSaveOK=false;
		GroupsSaveOK=false;
		recentReadReady=false;
		UsersSaveOK=false;
	}
	/**
	 * ���ļ����ö��룬�õ�User
	 * 
	 * @param file
	 *            �ļ�·��
	 */
	public static void getUser(String file) {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			loginUser = (ArrayList<User>) input.readObject();

			input.close();
			Userready = true;

		} catch(FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "û���û�������Ϣ�����������ɣ�\n�û�����admin\n���룺admin\n��½���뼰ʱ�޸����룡","��ʾ",JOptionPane.WARNING_MESSAGE);
			User user=new User();
			user.setUser("admin");
			user.setPsw("admin");
			Data.loginUser.add(user);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.print("Read setting file ERROR!");
			JOptionPane.showMessageDialog(null, "Read Setting File ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
			System.exit(0);//��������ֱ���˳�

		}
	}

	/**
	 * ���ļ����ö��룬�õ�Groups
	 * 
	 * @param file
	 *            �ļ�·��
	 */
	public static void getGroups(String file) {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			groups = (ArrayList<Groups>) input.readObject();

			input.close();
			Groupsready = true;
		} catch (FileNotFoundException e1) {
			// TODO: handle exception
			e1.printStackTrace();
			// file not find then we will create it
			// also help to first load
			writeFile(file,groups);
			Groupsready=true;
			JOptionPane.showMessageDialog(null, "�ƺ�û���ҵ���֯��Ϣ�ļ�","ERROR",JOptionPane.ERROR_MESSAGE);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.err.print("Read setting file ERROR!");
			JOptionPane.showMessageDialog(null, "Read Setting File ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 * ���ļ����ö��룬�õ�Group
	 * 
	 * @param file
	 *            �ļ�·��
	 */
	public static void getGroup(String file) {
		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			recentGroup = (Group)input.readObject();//save recent read and save it to the collection
			readGroup.add(recentGroup);
			groupReadID.add(recentGroup.ID);//add to read id

			input.close();

		} catch (FileNotFoundException e1) {
			// TODO: handle exception
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "�ƺ�û���ҵ���֯��Ϣ�ļ�","ERROR",JOptionPane.ERROR_MESSAGE);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.err.print("Read file ERROR!");
			JOptionPane.showMessageDialog(null, "Read File ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * ����Ϣд���ļ������е���Ϣ����һ����д��
	 * 
	 * @param file
	 *            �ļ�Ŀ¼
	 */
	public static void writeFile(String file,Object obj) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(obj);//������Ϣ

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
