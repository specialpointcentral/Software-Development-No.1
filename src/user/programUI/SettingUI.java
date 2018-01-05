package user.programUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import user.data.Data;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class SettingUI extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel txt_user;
	private JPasswordField pswOld;
	private JPasswordField pswNew;
	private JPasswordField pswConfirm;

	public SettingUI() {
		setBounds(0, 0, 575, 190);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u5BC6\u7801\u8BBE\u7F6E",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(14, 13, 279, 150);
		add(panel);
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

		pswOld = new JPasswordField();
		pswOld.setBounds(91, 20, 149, 24);
		panel.add(pswOld);

		pswNew = new JPasswordField();
		pswNew.setBounds(91, 51, 149, 24);
		panel.add(pswNew);

		pswConfirm = new JPasswordField();
		pswConfirm.setBounds(91, 82, 149, 24);
		panel.add(pswConfirm);

		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (new String(pswOld.getPassword()).equals(Data.user.getPassword()))
				// correct
				{
					if (new String(pswNew.getPassword()).equals(new String(pswConfirm.getPassword())))
					// setting
					{
						Data.user.setPsw(new String(pswNew.getPassword()));
						JOptionPane.showMessageDialog(null, "设置成功");
						pswOld.setText("");
						pswNew.setText("");
						pswConfirm.setText("");
					} else {
						pswNew.setText("");
						pswConfirm.setText("");
						JOptionPane.showMessageDialog(null, "两次密码不一致");
					}
				} else {
					pswOld.setText("");
					pswNew.setText("");
					pswConfirm.setText("");
					JOptionPane.showMessageDialog(null, "原密码错误");
				}
			}
		});
		button.setBounds(180, 115, 89, 27);
		panel.add(button);

		txt_user = new JLabel("{user}");
		txt_user.setBounds(14, 116, 152, 18);
		panel.add(txt_user);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u50A8\u5B58\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(297, 13, 255, 150);
		add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea txt_info = new JTextArea();
		txt_info.setEditable(false);
		txt_info.setBounds(14, 24, 227, 113);
		panel_1.add(txt_info);

		firstShow();
		new InfoThread(txt_info);
		setVisible(true);
	}

	/**
	 * show frame setting
	 */
	public void firstShow() {
		txt_user.setText("现在登陆的用户：" + Data.user.getUser());
	}
}
class InfoThread extends Thread{
	JTextArea txt_info;
	public InfoThread(JTextArea txt_info) {
		this.txt_info = txt_info;
		this.start();
	}
	public void run() {
		txt_info.setText("");
		txt_info.append("志愿者数据信息大小：");
		File file=new File("group"+Data.groupID+".dat");
		if(file.length()>5*1024&&file.length()<5*1024*1024) txt_info.append((file.length()/1024)+"KB");
		else if(file.length()>5*1024*1024) txt_info.append((file.length()/1024/1024)+"MB");
		else txt_info.append(file.length()+"B");
		txt_info.append("\n");
		txt_info.append("组织数据信息大小：");
		file=new File("groups.dat");
		if(file.length()>5*1024&&file.length()<5*1024*1024) txt_info.append((file.length()/1024)+"KB");
		else if(file.length()>5*1024*1024) txt_info.append((file.length()/1024/1024)+"MB");
		else txt_info.append(file.length()+"B");
		txt_info.append("\n");
	}
}
