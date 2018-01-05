package admin.programUI;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import admin.data.*;
import admin.process.GetGroupData;
import data.*;

import java.awt.Font;
import java.util.Iterator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTree tree_volTree;
	private JTextArea txt_volDetail;

	public InsertUI() {
		setLayout(null);

		JLabel lab_groupName = new JLabel("{groupName}");
		lab_groupName.setFont(new Font("����", Font.PLAIN, 16));
		lab_groupName.setBounds(14, 13, 232, 25);
		add(lab_groupName);
		lab_groupName.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lab_groupCode = new JLabel("{groupCode}");
		lab_groupCode.setFont(new Font("����", Font.PLAIN, 16));
		lab_groupCode.setBounds(252, 13, 161, 25);
		add(lab_groupCode);
		lab_groupCode.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel = new JPanel();
		panel.setBounds(14, 51, 672, 395);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(277, 13, 380, 369);
		panel.add(scrollPane);

		txt_volDetail = new JTextArea();
		scrollPane.setViewportView(txt_volDetail);
		txt_volDetail.setEditable(false);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(14, 13, 249, 369);
		panel.add(scrollPane_1);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("��֯");
		tree_volTree = new JTree(top);
		new BuildTreeThread(tree_volTree, top);
		scrollPane_1.setViewportView(tree_volTree);

		tree_volTree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_volTree.getLastSelectedPathComponent();
				if (node == null)
					return;
				else {
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// Ҷ�ڵ�,vol
						// ͷ������
						lab_groupName.setText(
								"��֯��" + ((Group) ((DefaultMutableTreeNode) node.getParent()).getUserObject()).name);
						lab_groupCode.setText(String.valueOf(
								"���룺" + ((Group) ((DefaultMutableTreeNode) node.getParent()).getUserObject()).ID));
						// �ĵ����ָ���
						txt_volDetail.setText("");
						txt_volDetail.append("������" + ((Volenteer) nodeInfo).name + "\n");
						txt_volDetail.append("ѧ�ţ�" + ((Volenteer) nodeInfo).IDnum + "\n");
						txt_volDetail.append("�Ա�" + ((Volenteer) nodeInfo).sex + "\n");
						txt_volDetail.append("״̬��" + ((Volenteer) nodeInfo).state + "\n");
						txt_volDetail.append("һ���μ�" + ((Volenteer) nodeInfo).activities.size() + "��־Ը�" + "\n");
						txt_volDetail.append("����־Ը�û�б���ˣ�" + "\n");
						Activity act = null;
						float time = 0f;
						int num = 0;
						for (Iterator<Activity> it = ((Volenteer) nodeInfo).activities.iterator(); it.hasNext();) {
							act = it.next();
							if (!act.checked) {// δ����˵���ʾ
								time += act.hour;
								txt_volDetail.append((++num) + ". (" + act.getMonth() + "/" + act.getDay() + ") "
										+ act.name + "-ʱ��" + act.hour + "\n");
							}
						}
						if (num == 0)
							txt_volDetail.append("û��δ����˵Ļ\n");
						txt_volDetail.append("δ����˵���ʱ��Ϊ" + time + "Сʱ");
					} else if (nodeInfo instanceof Groups) {
						// ���ڵ㣬δ��ȡ��Ϣ
						new BuildTreeVolThread(tree_volTree, node, ((Groups) nodeInfo).ID);
						// ͷ������
						lab_groupName.setText("��֯��" + ((Groups) nodeInfo).name);
						lab_groupCode.setText("���룺" + String.valueOf(((Groups) nodeInfo).ID));
						// �ĵ����ָ���

						txt_volDetail.setText("");
						txt_volDetail.append("��֯��" + ((Groups) nodeInfo).name + "\n");
						txt_volDetail.append("���룺" + ((Groups) nodeInfo).ID + "\n");
						// txt_volDetail.append("����" + Data.recentGroup.vol.size() + "��־Ը��" + "\n");
						tree_volTree.updateUI();

					} else if (nodeInfo instanceof Group) {
						// ���ڵ㣬�Ѷ�ȡ��Ϣ
						// ͷ������
						lab_groupName.setText("��֯��" + ((Group) nodeInfo).name);
						lab_groupCode.setText("���룺" + String.valueOf(((Group) nodeInfo).ID));
						// �ĵ����ָ���
						txt_volDetail.setText("");
						txt_volDetail.append("��֯��" + ((Group) nodeInfo).name + "\n");
						txt_volDetail.append("���룺" + ((Group) nodeInfo).ID + "\n");
						txt_volDetail.append("����" + ((Group) nodeInfo).vol.size() + "��־Ը��" + "\n");
					}
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 446, 672, 50);
		add(panel_1);
		panel_1.setLayout(null);

		JButton btn_insertData = new JButton("\u4ECE\u670D\u52A1\u5668\u83B7\u53D6\u6570\u636E");
		btn_insertData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// get data from server
				JFrame jf = new ReadUI();
				Thread thread = new Thread(new FileGetTread());
				thread.start();
				while (thread.isAlive()) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				jf.dispose();
			}
		});
		btn_insertData.setBounds(14, 8, 153, 39);
		panel_1.add(btn_insertData);

		JButton btn_edit = new JButton("\u7F16\u8F91");
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// modify
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_volTree.getLastSelectedPathComponent();
				if (node == null)
					return;
				else {
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// Ҷ�ڵ�,vol
						// open modify UI
						new IndividuActionChangeUI((Group) ((DefaultMutableTreeNode) node.getParent()).getUserObject(),
								(Volenteer) nodeInfo, true);

					} else
						return;
				}
			}
		});
		btn_edit.setBounds(415, 8, 113, 39);
		panel_1.add(btn_edit);

		JButton btn_confirm = new JButton("\u786E\u8BA4\u6838\u5BF9");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ȷ�ϰ�ť�������е�δ��ȷ�ϵĶ����ó�ȷ��
				if (JOptionPane.showConfirmDialog(null, "ȷ�����м�¼��", "����ȷ��", JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					// ֻȷ�϶�ȡ���ĵ���֯������readGroup���¼��
					Group group = null;
					for (Iterator<Group> it = Data.readGroup.iterator(); it.hasNext();) {
						group = it.next();
						new ActRecord(group);
					}

				}

			}
		});
		btn_confirm.setBounds(542, 8, 113, 39);
		panel_1.add(btn_confirm);

		JButton button = new JButton("\u5220\u9664");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "����", JOptionPane.WARNING_MESSAGE,
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_volTree.getLastSelectedPathComponent();
					if (node == null)
						return;
					else {
						Object nodeInfo = node.getUserObject();
						if (nodeInfo instanceof Volenteer) {
							// Ҷ�ڵ�,vol
							DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
							parent.remove(node);
							((Group) parent.getUserObject()).vol.remove(nodeInfo);
							tree_volTree.updateUI();

						} else
							JOptionPane.showMessageDialog(null, "�����ֹɾ����", "��ʾ", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		button.setBounds(319, 8, 82, 39);
		panel_1.add(button);

		// begging will set "-"
		lab_groupCode.setText("-");
		lab_groupName.setText("-");

	}
}

class BuildTreeThread extends Thread {
	JTree jt;
	DefaultMutableTreeNode top;

	public BuildTreeThread(JTree jt, DefaultMutableTreeNode top) {
		this.jt = jt;
		this.top = top;
		this.start();
	}

	public void run() {
		Groups groups = null;
		for (Iterator<Groups> it = Data.groups.iterator(); it.hasNext();) {
			groups = it.next();
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(groups);
			top.add(node);
		}
		jt.updateUI();
	}
}

class BuildTreeVolThread extends Thread {
	JTree jt;
	DefaultMutableTreeNode parant;
	int ID;

	public BuildTreeVolThread(JTree jt, DefaultMutableTreeNode parant, int ID) {
		this.jt = jt;
		this.parant = parant;
		this.ID = ID;
		this.start();
	}

	public void run() {
		new GetGroupData(ID);// this part has many problems
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

class ActRecord extends Thread {
	Group group;

	public ActRecord(Group group) {
		this.group = group;
		this.start();
	}

	public void run() {

		for (Iterator<Volenteer> it = group.vol.iterator(); it.hasNext();) {
			Activity act = null;
			for (Iterator<Activity> its = (it.next()).activities.iterator(); its.hasNext();) {
				act = its.next();
				if (act.checked == false)
					act.checked = true;
			}
		}
	}
}

class FileGetTread implements Runnable {

	public void run() {
		try {
			for (Iterator<Groups> it = Data.groups.iterator(); it.hasNext();) {
				new NetFileWork("group" + ((Groups) it.next()).ID + ".dat", 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}