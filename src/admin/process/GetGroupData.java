package admin.process;

import java.util.Iterator;

import javax.swing.JFrame;

import admin.data.Data;
import admin.programUI.ReadUI;
import data.*;

/**
 * this class is using to get group info, if it at group list return it; else we
 * will read it ,save it to the collection then return it
 * 
 * @author huqi1
 *
 */
public class GetGroupData {
	public GetGroupData(int ID) {
		JFrame jf=new ReadUI();
		// ID is group ID

		// first find it in the list
		if (Data.groupReadID.contains(ID)) {
			// have it & find it
			Group group = null;
			for (Iterator<Group> it = Data.readGroup.iterator(); it.hasNext();) {
				group = it.next();
				if (group.ID == ID) {// got it
					Data.recentGroup = group;
				}
			}

		} else {
			// no
			
			ReadData.readGroup(Integer.valueOf(ID));
			// store recent group
			while(!Data.recentReadReady) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		jf.dispose();

	}
	

}

