package user.process;

import data.NetFileWork;
import user.data.Data;

public class ReadData {
	/**
	 * using to read user info
	 */
	public static void readGroupUser() {
		new ReadGroupUser();
	}
	/**
	 * using to read groups info
	 * @param ID group ID
	 */
	public static void readGroup(int ID) {
		new ReadGroup(ID);
	}

}

class ReadGroupUser extends Thread {
	public ReadGroupUser() {
		this.start();
	}

	public void run() {
		try {
			new NetFileWork("groups.dat", 1);
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data.getGroupUser("groups.dat");
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
		Data.getGroup("group" + ID + ".dat");
	}

}