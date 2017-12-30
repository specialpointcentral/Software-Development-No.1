package admin.programUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import data.*;
import admin.data.Data;
import admin.process.GetGroupData;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class ManageUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<Groups> list1;
	private DefaultListModel<Volenteer> list2;
	private JTextArea txt_volSimpleInfo;

	public ManageUI() {
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		setTitle("\u4FE1\u606F\u7BA1\u7406-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 516, 429);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(14, 13, 222, 356);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 242, 219, 114);
		panel.add(scrollPane);

		JTextArea txt_groupInfo = new JTextArea();
		txt_groupInfo.setEditable(false);
		scrollPane.setViewportView(txt_groupInfo);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 219, 237);
		panel.add(scrollPane_2);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("������֯");
		JTree tree = new JTree(top);
		scrollPane_2.setViewportView(tree);

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node == null)
					return;
				else {
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// Ҷ�ڵ�,vol
						// �ĵ����ָ���
						txt_volSimpleInfo.setText("");
						txt_volSimpleInfo.append("������" + ((Volenteer) nodeInfo).name + "\n");
						txt_volSimpleInfo.append("ѧ�ţ�" + ((Volenteer) nodeInfo).IDnum + "\n");
						txt_volSimpleInfo.append("�Ա�" + ((Volenteer) nodeInfo).sex + "\n");
						txt_volSimpleInfo.append("״̬��" + ((Volenteer) nodeInfo).state + "\n");
						txt_volSimpleInfo.append("һ���μ�" + ((Volenteer) nodeInfo).activities.size() + "��־Ը�" + "\n");
						Activity act = null;
						float timecheck = 0f, timeuncheck = 0f;
						int num = 0;
						for (Iterator<Activity> it = ((Volenteer) nodeInfo).activities.iterator(); it.hasNext();) {
							act = it.next();
							if (!act.checked) {// δ����˵���ʾ
								timeuncheck += act.hour;
								txt_volSimpleInfo.append((++num) + ". | δ��� | (" + act.getMonth() + "/" + act.getDay()
										+ ") " + act.name + "-ʱ��" + act.hour + "\n");
							} else {
								timecheck += act.hour;
								txt_volSimpleInfo.append((++num) + ". (" + act.getMonth() + "/" + act.getDay() + ") "
										+ act.name + "-ʱ��" + act.hour + "\n");
							}
						}
						txt_volSimpleInfo
								.append("��ʱ��Ϊ" + (timecheck + timeuncheck) + "\n����δ����˵���ʱ��Ϊ" + timeuncheck + "Сʱ");
					} else if (nodeInfo instanceof Groups) {
						// ���ڵ㣬δ��ȡ��Ϣ
						new BuildTreeVol(tree, node, ((Groups) nodeInfo).ID);
						// �ĵ����ָ���
						txt_groupInfo.setText("");
						txt_groupInfo.append("��֯��" + ((Groups) nodeInfo).name + "\n");
						txt_groupInfo.append("���룺" + ((Groups) nodeInfo).ID + "\n");
						// txt_groupInfo.append("����" + Data.recentGroup.vol.size() + "��־Ը��" + "\n");

					} else if (nodeInfo instanceof Group) {
						// ���ڵ㣬�Ѷ�ȡ��Ϣ
						// �ĵ����ָ���
						txt_groupInfo.setText("");
						txt_groupInfo.append("��֯��" + ((Group) nodeInfo).name + "\n");
						txt_groupInfo.append("���룺" + ((Group) nodeInfo).ID + "\n");
						txt_groupInfo.append("����" + ((Group) nodeInfo).vol.size() + "��־Ը��" + "\n");
					}
				}
			}
		});

		new ShowGroupTree(top, tree);// ����

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(239, 13, 261, 356);
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);

		JButton btn_infoModify = new JButton("\u4FEE\u6539");
		btn_infoModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node == null)
					return;
				else {
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// Ҷ�ڵ�,vol
						new IndividuActionChangeUI((Group)((DefaultMutableTreeNode)node.getParent()).getUserObject(), 
								(Volenteer)nodeInfo, false);

					} else
						JOptionPane.showMessageDialog(null, "�������޸Ĵ˽�㣡", "����", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_2.add(btn_infoModify);

		JButton btn_infoDetail = new JButton("\u8BE6\u7EC6\u4FE1\u606F");
		btn_infoDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btn_infoDetail);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);

		txt_volSimpleInfo = new JTextArea();
		txt_volSimpleInfo.setEditable(false);
		scrollPane_1.setViewportView(txt_volSimpleInfo);

		setResizable(false);// ���ɷŴ�
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new MainUI();
				dispose();
			}

		});
	}
}

class ShowGroupTree extends Thread {
	DefaultMutableTreeNode node;
	JTree jt;

	public ShowGroupTree(DefaultMutableTreeNode node, JTree jt) {
		this.node = node;
		this.jt = jt;
		this.start();
	}

	public void run() {
		for (Iterator<Groups> it = Data.groups.iterator(); it.hasNext();) {
			DefaultMutableTreeNode nodes = new DefaultMutableTreeNode(it.next());
			node.add(nodes);
		}
		jt.updateUI();
	}

}

class BuildTreeVol extends Thread {
	JTree jt;
	DefaultMutableTreeNode parant;
	int ID;

	public BuildTreeVol(JTree jt, DefaultMutableTreeNode parant, int ID) {
		this.jt = jt;
		this.parant = parant;
		this.ID = ID;
		this.start();
	}

	public void run() {
		new GetGroupData(ID);
		Volenteer vol = null;
		for (Iterator<Volenteer> it = Data.recentGroup.vol.iterator(); it.hasNext();) {
			vol = it.next();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(vol);
			parant.add(node);
		}
		// insert is OK
		parant.setUserObject(Data.recentGroup);
	}
}
