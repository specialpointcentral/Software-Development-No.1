package user.programUI;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JFrame;
import javax.swing.JLabel;

import user.data.Data;
import javax.swing.JTabbedPane;

import user.programUI.SettingUI;

import javax.swing.JPanel;

public class MainUIPanel extends JFrame {
	JLabel txt_groupName;
	JLabel txt_code;
	JLabel txt_user;
	private JPanel volManageSet;
	private JPanel setting;
	public MainUIPanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\u4E3B\u754C\u9762-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setBounds(100, 100, 620, 618);
		getContentPane().setLayout(null);

		txt_groupName = new JLabel("{groupName}");
		txt_groupName.setBounds(24, 13, 150, 18);
		getContentPane().add(txt_groupName);

		txt_code = new JLabel("{Code}");
		txt_code.setBounds(257, 13, 79, 18);
		getContentPane().add(txt_code);

		txt_user = new JLabel("{user}");
		txt_user.setBounds(257, 33, 79, 18);
		getContentPane().add(txt_user);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(24, 44, 573, 524);
		getContentPane().add(tabbedPane);
		
		JPanel insertRecordSet = new InsertRecordUI();
		tabbedPane.addTab("\u65F6\u957F\u5F55\u5165",  insertRecordSet);
		
		volManageSet = new VolManageUI();
		tabbedPane.addTab("\u4FE1\u606F\u7BA1\u7406",  volManageSet);
		
		setting = new SettingUI();
		tabbedPane.addTab("设置", setting);
		
		
		firstShow();
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new SaveUI(1);//保存作业
			}

		});
	}
	/**
	 * show frame setting
	 */
	public void firstShow() {
		txt_groupName.setText("组织名称："+Data.groupName);
		txt_code.setText("ID："+String.valueOf(Data.groupID));
		txt_user.setText("操作员："+Data.loginUser);
	}
}
