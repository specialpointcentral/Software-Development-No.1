package admin.programUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;

import admin.data.Data;
import data.User;
import admin.programUI.MainUIPanel;
import admin.process.ReadData;

public class LoginUI extends JFrame implements ActionListener {
	/**
	 * LoginUI ��½����
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jp1, jp2, jp3, jp4, jp5;
	private JButton btn1, btn2;
	private JLabel lb1, lb2;
	private JTextField user;
	private JPasswordField password;

	public LoginUI() {
		super("��¼ϵͳ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp1 = new JPanel();// ���ڴ�ű���
		jp1.add(new JLabel("��ӭ��½����ϵͳ"));
		jp2 = new JPanel();// ���ڴ�ŵ�¼��ʾ
		jp2.setLayout(new GridLayout(2, 1, 15, 15));
		lb1 = new JLabel("�û���");
		lb2 = new JLabel("��   ��");
		jp2.add(lb1);
		jp2.add(lb2);
		jp3 = new JPanel();// ���ڴ�ŵ�½����
		jp3.setLayout(new GridLayout(2, 1, 15, 15));
		user = new JTextField(20);
		password = new JPasswordField(20);
		jp3.add(user);
		jp3.add(password);
		jp4 = new JPanel(new BorderLayout());// ���ڴ�����
		jp4.add(jp2, BorderLayout.WEST);
		jp4.add(jp3, BorderLayout.CENTER);
		jp5 = new JPanel();// ���ڴ�Ű�ť
		btn1 = new JButton("��½");
		btn2 = new JButton("����");
		jp5.add(btn1);
		jp5.add(btn2);

		getContentPane().setLayout(new BorderLayout(10, 10));// ������ʽ

		getContentPane().add(jp1, BorderLayout.NORTH);
		getContentPane().add(jp4, BorderLayout.CENTER);
		getContentPane().add(jp5, BorderLayout.SOUTH);

		// ע�ᰴť
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if (source == btn1) {
			// �ύ��ť
			String users, passwords;
			users=user.getText().toString();
			passwords=new String (password.getPassword());
			System.out.println(users+" "+passwords);
			User loginUser=null;
			for(Iterator<User> it=Data.loginUser.iterator();it.hasNext();) {
				//user is exist? password is correct?
				loginUser=it.next();
				System.out.println(loginUser.getUser());
				System.out.println(loginUser.getPassword());
				if(loginUser.getUser().equals(users)&&loginUser.getPassword().equals(passwords)) {
					//correct!
					Data.user=loginUser.getUser();
					ReadData.readGroups();
					new MainUIPanel();
					dispose();
				}
			}
			//is not correct
			user.setText("");
			password.setText("");

		} else if (source == btn2) {
			// ���ð�ť
			user.setText("");
			password.setText("");
		}
	}

}
