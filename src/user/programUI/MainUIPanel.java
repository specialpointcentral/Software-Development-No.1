package user.programUI;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JFrame;
import javax.swing.JLabel;

import user.data.Data;
import javax.swing.JTabbedPane;

public class MainUIPanel extends JFrame {
	JLabel txt_groupName;
	JLabel txt_code;
	JLabel txt_user;
	public MainUIPanel() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\u4E3B\u754C\u9762-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setBounds(100, 100, 582, 461);
		getContentPane().setLayout(null);

		txt_groupName = new JLabel("{groupName}");
		txt_groupName.setBounds(14, 13, 150, 18);
		getContentPane().add(txt_groupName);

		txt_code = new JLabel("{Code}");
		txt_code.setBounds(237, 13, 79, 18);
		getContentPane().add(txt_code);

		txt_user = new JLabel("{user}");
		txt_user.setBounds(237, 33, 79, 18);
		getContentPane().add(txt_user);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(24, 44, 538, 369);
		getContentPane().add(tabbedPane);
		
		
		firstShow();
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new SaveUI();//±£´æ×÷Òµ
				dispose();
			}

		});
	}
	/**
	 * show frame setting
	 */
	public void firstShow() {
		txt_groupName.setText(Data.groupName);
		txt_code.setText(String.valueOf(Data.groupID));
		txt_user.setText(Data.loginUser);
	}
}
