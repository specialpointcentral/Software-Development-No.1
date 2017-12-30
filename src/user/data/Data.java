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
	public static String groupName;// 组织信息
	public static int groupID;// 组织ID
	public static String loginUser;// 登陆进去的用户
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
	 * 将文件配置读入，得到User
	 * 
	 * @param file
	 *            文件路径
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
	 * 将文件配置读入，得到Groups
	 * 
	 * @param file
	 *            文件路径
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
			JOptionPane.showMessageDialog(null, "似乎没有找到志愿者信息文件","ERROR",JOptionPane.ERROR_MESSAGE);

		} catch (Exception e2) {
			e2.printStackTrace();
			System.err.print("Read setting file ERROR!");
			JOptionPane.showMessageDialog(null, "Read Setting File ERROR","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 将信息写入文件，所有的信息将会一次性写入
	 * 
	 * @param file
	 *            文件目录
	 */
	public static void writeFile(String file) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(group);//存入志愿者等等信息

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
