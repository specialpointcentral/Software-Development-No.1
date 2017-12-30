package user.programUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import user.data.Data;
import user.process.Test;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class MainUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel txt_groupName;
	JLabel txt_code;
	JLabel txt_user;

	public MainUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\u4E3B\u754C\u9762-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setBounds(100, 100, 336, 209);
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

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u7BA1\u7406", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(14, 57, 302, 103);
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btn_insert = new JButton("\u65F6\u957F\u5F55\u5165");
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new InsertRecordUI();
				dispose();
			}
		});
		btn_insert.setBounds(27, 24, 113, 27);
		panel.add(btn_insert);

		JButton btn_setting = new JButton("\u8BBE\u7F6E");
		btn_setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new Test();
			}
		});
		btn_setting.setBounds(164, 24, 113, 27);
		panel.add(btn_setting);

		JButton btn_manage = new JButton("\u4FE1\u606F\u7BA1\u7406");
		btn_manage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VolManageUI();
				dispose();
			}
		});
		btn_manage.setBounds(27, 63, 113, 27);
		panel.add(btn_manage);

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
