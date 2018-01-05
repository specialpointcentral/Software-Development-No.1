package user.process;

import data.NetFileWork;
import user.data.Data;

public class WriteData {
	/**
	 * 
	 * @param ID
	 *            groupID
	 * @param type
	 *            1-write group 2-write groups
	 */
	public WriteData(int ID, int type) {
		if (type == 1)
			new WriteGroup(ID);
		else
			new WriteGroups();
	}
}

class WriteGroup extends Thread {
	int ID;

	public WriteGroup(int ID) {
		this.ID = ID;
		this.start();
	}

	public void run() {
		Data.writeGroup("group" + ID + ".dat");
		try {
			Thread.sleep(100);
			new NetFileWork("group" + ID + ".dat", 2);
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data.saveGroup = true;
	}

}

class WriteGroups extends Thread {

	public WriteGroups() {
		this.start();
	}

	public void run() {
		Data.writeUser("groups.dat");
		try {
			Thread.sleep(100);
			new NetFileWork("groups.dat", 2);
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Data.saveGroups = true;
	}

}