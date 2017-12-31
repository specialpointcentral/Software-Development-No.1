package user.programUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import user.data.Data;
import data.*;
import user.process.ReadData;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class LoginUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField txt_userName;
	JPasswordField txt_passWord;
	JComboBox<String> comboBox;

	public LoginUI() {
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u767B\u9646-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setBounds(100, 100, 333, 251);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(14, 13, 302, 188);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setBounds(14, 13, 72, 18);
		panel.add(label);

		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setBounds(14, 44, 72, 18);
		panel.add(label_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u7EC4\u7EC7\u9009\u62E9",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(14, 75, 276, 64);
		panel.add(panel_1);
		panel_1.setLayout(null);

		comboBox = new JComboBox();
		comboBox.setBounds(14, 27, 248, 24);
		panel_1.add(comboBox);

		JButton btn_confirm = new JButton("\u786E\u5B9A");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// login
				String pwd = new String(txt_passWord.getPassword());
				if (loginCheck(txt_userName.getText(), pwd, comboBox.getSelectedItem().toString(),
						Data.groups) == true) {
					// 成功登陆
					// System.out.println(txt_userName.getText()+ ","+pwd);
					try {
						new NetFileWork("group" + Data.groupID + ".dat", 1);
						Thread.sleep(100);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ReadData.readGroup(Data.groupID);// 读取信息
					new ReadUI();
					while (true) {
						if (Data.Groupready) {
							new MainUI();// 打开MainUI
							break;
						}
						//System.out.println(Data.Groupready);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					dispose();

				} else {
					// 登录失败
					setNull();// 恢复初始
				}

			}
		});
		btn_confirm.setBounds(24, 152, 113, 27);
		panel.add(btn_confirm);

		JButton btn_reset = new JButton("\u91CD\u7F6E");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setNull();// 恢复初始
			}
		});
		btn_reset.setBounds(167, 152, 113, 27);
		panel.add(btn_reset);

		txt_userName = new JTextField();
		txt_userName.setBounds(69, 10, 205, 24);
		panel.add(txt_userName);
		txt_userName.setColumns(10);

		txt_passWord = new JPasswordField();
		txt_passWord.setBounds(69, 44, 205, 24);
		panel.add(txt_passWord);
		setNull();// 初始化
		setVisible(true);
	}
	public static boolean loginCheck(String userName,String password,String groupName,Collection<Groups> groups) {//判断是否登陆成功
		Groups group=null;
		boolean flag=false;//is find?
		for(Iterator<Groups> it=groups.iterator();it.hasNext();) {
			group=it.next();
			if(group.name.equals(groupName)) {
				//找到组织
				flag=true;
				break;
			}
		}
		if(flag==true) {
			User user =null;flag=false;
			for(Iterator<User> it=group.users.iterator();it.hasNext();) {
				//查找用户
				user=it.next();
				if(user.getUser().equals(userName)&&user.getPassword().equals(password)) {
					//确认正确，变量存入
					Data.groupName=groupName;
					Data.groupID=group.ID;
					Data.loginUser=userName;
					flag=true;
				}
			}
			if(flag==false) return false;	
			else return true;
		}else return false;
		
	}

	private void setNull() {
		// 设置成初始值
		txt_passWord.setText("");
		txt_userName.setText("");
		Groups gp = null;// 暂存group
		comboBox.removeAllItems();
		for (Iterator<Groups> it = Data.groups.iterator(); it.hasNext();) {
			gp = it.next();
			comboBox.addItem(gp.name);
		}
		comboBox.setSelectedIndex(0);

	}

}
