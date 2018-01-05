package admin.process;

import admin.data.Data;
import data.NetFileWork;

public class ReadData {
	/**
	 * using to read user info
	 */
	public static void readUser() {
		new ReadUser();
	}

	/**
	 * using to read group info
	 * 
	 * @param ID
	 *            group ID
	 */
	public static void readGroup(int ID) {
		new ReadGroup(ID);

	}

	/**
	 * using to read all group info
	 * 
	 */
	public static void readGroups() {
		ReadGroups th=new ReadGroups();
		while(th.isAlive()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}

class ReadUser extends Thread {
	public ReadUser() {
		this.start();
	}

	public void run() {
		try {
			new NetFileWork("config.dat", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Data.getUser("config.dat");
	}

}

class ReadGroup extends Thread {
	int ID;

	/**
	 * Read Group info
	 * 
	 * @param ID
	 *            using to identify which group
	 */
	public ReadGroup(int ID) {
		this.ID = ID;
		this.start();
	}

	public void run() {
//		try {
//			new NetFileWork("group" + ID + ".dat", 1);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		Data.getGroup("group" + ID + ".dat");
		Data.recentReadReady = true;
	}

}

class ReadGroups extends Thread {
	public ReadGroups() {
		this.start();
	}

	public void run() {
		try {
			new NetFileWork("groups.dat", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Data.getGroups("groups.dat");
	}

}