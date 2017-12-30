package user.process;

import user.data.Data;
import data.*;

public class Test extends Thread{

	public Test(){
		this.start();
	}
	public void run() {
		for(int i=0;i<50;i++) {
			Volenteer vol=new Volenteer();
			vol.name="Jack";
			vol.IDnum=100000;
			vol.sex="ÄĞ";
			Activity act=new Activity();
			act.group=Data.group;
			act.name="help";
			vol.activities.add(act);
			Data.group.vol.add(vol);
		}
	}
}
