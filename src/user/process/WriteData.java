package user.process;

import data.NetFileWork;
import user.data.Data;

public class WriteData {
	boolean groupOK = false;
	boolean configOK = false;

	public WriteData(int ID) {
		new WriteGroup(ID);

	}
}

class WriteGroup extends Thread {
	int ID;

	public WriteGroup(int ID) {
		this.ID = ID;
		this.start();
	}

	public void run() {
		Data.writeFile("group" + ID + ".dat");
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