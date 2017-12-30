package user;

import user.data.Data;
import user.process.ReadData;
import user.programUI.LoginUI;
import user.programUI.MainUIPanel;

public class Start {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		new Data();//first set Data
		// read user list
		ReadData.readGroupUser();//read first
		//-----------Test---------------
//		Groups grop=new Groups();
//		Data.groups=new ArrayList<Groups>();
//		grop.ID=1;
//		grop.name="Test";
//		grop.users.add(new User("Test"));
//		Data.groups.add(grop);
//		Data.Userready=true;
		
//		Data.group=new Group();
//		Data.group.name="Test";
//		Data.group.ID=1;
//		Volenteer vol=new Volenteer();
//		vol.name="Jack";
//		vol.IDnum=100000;
//		vol.sex="ÄÐ";
//		Activity act=new Activity();
//		act.group=Data.group;
//		act.name="help";
//		vol.activities.add(act);
//		Data.group.vol.add(vol);
//		
//		Data.groupID=1;
		
		
		//----------Test over--------------
		//get data from server
//		try {
//			new NetFileWork("config.dat", 1);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		while(true) {
			//Data is ready?
			Thread.sleep(100);
			if(Data.Userready) {
				new LoginUI();
				new MainUIPanel();
				break;
			}
			
		}

	}
	
}
