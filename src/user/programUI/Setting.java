package user.programUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import user.data.Data;
import javax.swing.JPasswordField;

public class Setting extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel txt_groupName;
	JLabel txt_code;
	JLabel txt_user;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	public Setting() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u8BBE\u7F6E-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setBounds(100, 100, 314, 352);
		getContentPane().setLayout(null);

		txt_groupName = new JLabel("{groupName}");
		txt_groupName.setBounds(14, 13, 150, 18);
		getContentPane().add(txt_groupName);

		txt_code = new JLabel("{Code}");
		txt_code.setBounds(227, 13, 79, 18);
		getContentPane().add(txt_code);

		txt_user = new JLabel("{user}");
		txt_user.setBounds(237, 33, 79, 18);
		getContentPane().add(txt_user);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u5BC6\u7801\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(14, 48, 279, 150);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u539F\u5BC6\u7801");
		label.setBounds(14, 23, 72, 18);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u65B0\u5BC6\u7801");
		label_1.setBounds(14, 54, 72, 18);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u518D\u6B21\u8F93\u5165");
		label_2.setBounds(14, 85, 72, 18);
		panel.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(91, 20, 149, 24);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(91, 51, 149, 24);
		panel.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(91, 82, 149, 24);
		panel.add(passwordField_2);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setBounds(180, 115, 89, 27);
		panel.add(button);

		firstShow();
		setVisible(true);
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
