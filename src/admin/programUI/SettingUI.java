package admin.programUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class SettingUI extends JFrame{
	private JTextField txt_userName;
	private JPasswordField txt_oldPW;
	private JPasswordField txt_newPW;
	private JPasswordField txt_confirmPW;
	public SettingUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("\u8BBE\u7F6E-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setBounds(100, 100, 290, 267);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 13, 244, 188);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "\u5BC6\u7801\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u539F\u5BC6\u7801");
		label.setBounds(14, 55, 72, 18);
		panel.add(label);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setBounds(14, 26, 72, 18);
		panel.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("\u65B0\u5BC6\u7801");
		label_1.setBounds(14, 86, 72, 18);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_2.setBounds(14, 117, 72, 18);
		panel.add(label_2);
		
		txt_userName = new JTextField();
		txt_userName.setBounds(100, 23, 124, 24);
		panel.add(txt_userName);
		txt_userName.setColumns(10);
		
		txt_oldPW = new JPasswordField();
		txt_oldPW.setBounds(100, 52, 124, 24);
		panel.add(txt_oldPW);
		
		txt_newPW = new JPasswordField();
		txt_newPW.setBounds(100, 83, 124, 24);
		panel.add(txt_newPW);
		
		txt_confirmPW = new JPasswordField();
		txt_confirmPW.setBounds(100, 114, 124, 24);
		panel.add(txt_confirmPW);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setBounds(152, 151, 72, 27);
		panel.add(button);
		
		setVisible(true);
	}
}
