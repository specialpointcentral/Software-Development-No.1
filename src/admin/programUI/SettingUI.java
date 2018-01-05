package admin.programUI;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import admin.data.Data;
import data.Groups;
import data.User;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.util.Iterator;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class SettingUI extends JPanel {
	private int pswMode;// 1-insert 2-modify
	private JTextField txt_userName;
	private JPasswordField txt_oldPW;
	private JPasswordField txt_newPW;
	private JPasswordField txt_confirmPW;
	private JList<User> list_user;
	private DefaultListModel<User> line;

	public SettingUI() {

		setBounds(100, 100, 720, 388);
		setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u5BC6\u7801\u66F4\u6539",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(14, 13, 482, 271);
		add(panel_1);
		panel_1.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(224, 29, 244, 188);
		panel_1.add(panel);
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
		txt_userName.setEditable(false);
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
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User select = list_user.getSelectedValue();

				// first identify password is right
				boolean tag = false;// user and psw is right
				if (pswMode == 2) {
					User user = null;
					for (Iterator<User> it = Data.loginUser.iterator(); it.hasNext();) {
						user = it.next();
						if (user.getUser().equals(select.getUser())
								&& user.getPassword().equals(new String(txt_oldPW.getPassword()))) {
							// correct
							tag = true;
						}
					}
				} else
					tag = true;
				// second password is right in two box
				if (tag) {
					if ((new String(txt_newPW.getPassword())).equals(new String(txt_confirmPW.getPassword()))) {
						if (pswMode == 2) {
							// modify
							select.setPsw(new String(txt_newPW.getPassword()));
						} else {
							// final write in
							User newUser = new User();
							newUser.setUser(txt_userName.getText());
							newUser.setPsw(new String(txt_newPW.getPassword()));
							Data.loginUser.add(newUser);
							line.addElement(newUser);
						}

					} else
						JOptionPane.showMessageDialog(null, "原密码错误！", "提示", JOptionPane.INFORMATION_MESSAGE);

				} else
					JOptionPane.showMessageDialog(null, "原密码错误！", "提示", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		button.setBounds(152, 151, 72, 27);
		panel.add(button);

		JButton button_1 = new JButton("\u5220\u9664");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "是否删除？", "警告", JOptionPane.WARNING_MESSAGE,
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					User select = list_user.getSelectedValue();
					Data.loginUser.remove(select);// 系统去除
					line.removeElement(select);// 列表去除
				}

			}
		});
		button_1.setBounds(14, 151, 72, 27);
		panel.add(button_1);

		JButton button_2 = new JButton("\u63D2\u5165");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pswMode = 1;
				txt_userName.setEditable(true);
				txt_userName.setText("");
				txt_newPW.setText("");
				txt_confirmPW.setText("");
				txt_oldPW.setEditable(false);
				button_2.setEnabled(false);
				list_user.clearSelection();
			}
		});
		button_2.setBounds(88, 151, 63, 27);
		panel.add(button_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 28, 210, 230);
		panel_1.add(scrollPane);

		list_user = new JList<User>();
		scrollPane.setViewportView(list_user);
		line = new DefaultListModel<User>();
		showList(list_user, line);// show list
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u5B58\u50A8\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(499, 13, 207, 271);
		add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 23, 179, 235);
		panel_2.add(scrollPane_1);
		
		JTextArea txt_info = new JTextArea();
		txt_info.setEditable(false);
		scrollPane_1.setViewportView(txt_info);
		setVisible(true);
		list_user.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO 自动生成的方法存根
				if (!e.getValueIsAdjusting() && list_user.getSelectedValue() != null) {
					User user = list_user.getSelectedValue();
					txt_userName.setText(user.getUser());
					button_2.setEnabled(true);
					txt_userName.setEditable(false);// 不能修改
					txt_oldPW.setEditable(true);
					pswMode = 2;// modify
					txt_newPW.setText("");
					txt_confirmPW.setText("");
				}

			}
		});
		new InfoThread(txt_info);

	}

	public void showList(JList<User> list, DefaultListModel<User> line) {
		list.setModel(line);
		for (Iterator<User> it = Data.loginUser.iterator(); it.hasNext();) {
			line.addElement(it.next());
		}
		list.updateUI();

	}
}
class InfoThread extends Thread{
	JTextArea txt_info;
	public InfoThread(JTextArea txt_info) {
		this.txt_info = txt_info;
		this.start();
	}
	public void run() {
		File file;
		txt_info.setText("");
		txt_info.append("组织数据信息大小：");
		file=new File("groups.dat");
		if(file.length()>5*1024&&file.length()<5*1024*1024) txt_info.append((file.length()/1024)+"KB");
		else if(file.length()>5*1024*1024) txt_info.append((file.length()/1024/1024)+"MB");
		else txt_info.append(file.length()+"B");
		txt_info.append("\n");
		//志愿者信息
		Groups groups=null;
		txt_info.append("志愿者数据信息大小：\n");
		for(Iterator<Groups>it=Data.groups.iterator();it.hasNext();) {
			groups=it.next();
			file=new File("group"+groups.ID+".dat");
			if(file.exists()) {
				//存在
				txt_info.append(file.getName()+" ");
				if(file.length()>5*1024&&file.length()<5*1024*1024) txt_info.append((file.length()/1024)+"KB");
				else if(file.length()>5*1024*1024) txt_info.append((file.length()/1024/1024)+"MB");
				else txt_info.append(file.length()+"B");
				txt_info.append("\n");
			}
		}
	}
}
