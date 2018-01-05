package admin.programUI;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.data.Data;
import javax.swing.JTabbedPane;

public class MainUIPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainUIPanel() {
		setTitle("\u4E3B\u754C\u9762-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100,100, 745, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 720, 36);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lab_userName = new JLabel("{userName}");
		panel.add(lab_userName, BorderLayout.EAST);
		setResizable(false);//���ɷŴ�
		lab_userName.setText("����Ա��"+Data.user);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 43, 720, 549);
		contentPane.add(tabbedPane);
		
		JPanel InsertUI = new InsertUI();
		tabbedPane.addTab("������Ϣ�˶�" , InsertUI);
		
		JPanel ManageUI = new ManageUI();
		tabbedPane.addTab("��Ϣ����", ManageUI);
		
		JPanel GroupsManageUI = new GroupsManageUI();
		tabbedPane.addTab("��֯��Ϣ����", GroupsManageUI);
		
		JPanel Setting = new SettingUI();
		tabbedPane.addTab("����", Setting);
		
		
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new SaveUI();//������ҵ
				dispose();
			}

		});
	}
}
