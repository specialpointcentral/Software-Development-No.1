package admin;

import admin.process.ReadData;
import admin.programUI.LoginUI;
import admin.data.Data;
import data.*;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//first start data
		new Data();
		
		//read config data
		ReadData.readUser();
	
		new LoginUI();


	}

}
