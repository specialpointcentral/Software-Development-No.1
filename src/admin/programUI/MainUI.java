package admin.programUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.data.Data;
import admin.programUI.SaveUI;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainUI() {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		setTitle("\u4E3B\u754C\u9762-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 245);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 434, 36);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lab_userName = new JLabel("{userName}");
		panel.add(lab_userName, BorderLayout.EAST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 48, 434, 152);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btn_setting = new JButton("\u8BBE\u7F6E");
		btn_setting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_setting.setBounds(313, 29, 95, 40);
		panel_1.add(btn_setting);
		
		JButton btn_insert = new JButton("\u6570\u636E\u4FE1\u606F\u6838\u5BF9");
		btn_insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InsertUI();
				dispose();
			}
		});
		btn_insert.setBounds(14, 29, 123, 40);
		panel_1.add(btn_insert);
		
		JButton btn_manager = new JButton("\u4FE1\u606F\u7BA1\u7406");
		btn_manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageUI();
				dispose();
			}
		});
		btn_manager.setBounds(151, 29, 123, 40);
		panel_1.add(btn_manager);
		
		JButton btn_export = new JButton("\u4FE1\u606F\u5BFC\u51FA");
		btn_export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btn_export.setBounds(14, 88, 123, 40);
		panel_1.add(btn_export);
		
		JButton btn_checkGroups = new JButton("\u7EC4\u7EC7\u4FE1\u606F\u7BA1\u7406");
		btn_checkGroups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GroupsManageUI();
				dispose();
			}
		});
		btn_checkGroups.setBounds(151, 88, 123, 40);
		panel_1.add(btn_checkGroups);
		setResizable(false);//不可放大
		lab_userName.setText(Data.user);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new SaveUI();//保存作业
				dispose();
			}

		});
	}
}
