package admin.process;

import java.util.ArrayList;
import java.util.Iterator;

import admin.data.Data;
import data.*;

public class WriteData {
	boolean groupOK = false;
	boolean configOK = false;

	public WriteData(int tag, Object obj) {
		// tag 1:ArryList<Group> 2:ArryList<Groups>
		switch (tag) {
		case 1:
			ArrayList<Group> group = (ArrayList<Group>) obj;
			new WriteGroup(group);
			break;
		case 2:
			ArrayList<Groups> groups = (ArrayList<Groups>) obj;
			new WriteGroups(groups);
			break;
		}

	}
}

class WriteGroup extends Thread {
	ArrayList<Group> groups;

	public WriteGroup(ArrayList<Group> group) {
		this.groups = group;
		this.start();
	}

	public void run() {
		// 将打开过的信息保存到各个文件
		Group group = null;
		for (Iterator<Group> it = groups.iterator(); it.hasNext();) {
			group = it.next();
			Data.writeFile("group" + group.ID + ".dat", group);
			try {
				new NetFileWork("group" + group.ID + ".dat", 2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Data.GroupSaveOK = true;

	}
}

class WriteGroups extends Thread {
	ArrayList<Groups> groups;

	public WriteGroups(ArrayList<Groups> groups) {
		this.groups = groups;
		this.start();
	}

	public void run() {
		Data.writeFile("groups.dat", groups);
		try {
			new NetFileWork("groups.dat", 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Data.GroupsSaveOK = true;
	}
}

class WriteUser extends Thread {
	ArrayList<User> users;

	public WriteUser(ArrayList<User> users) {
		this.users = users;
		this.start();
	}

	public void run() {
		// maybe config.data can save more infomation
		Data.writeFile("config.dat", users);
	}
}