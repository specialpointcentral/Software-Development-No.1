package user.programUI;

import javax.swing.JFrame;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import data.*;
import user.data.Data;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JTextArea;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VolManageUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextArea txt_info;
	private JButton btn_modify;
	private JButton btn_delete;

	public VolManageUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\u5FD7\u613F\u8005\u7BA1\u7406-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		
		JPanel jp=new JPanel();
		jp.setLayout(null);
		jp.setBounds(100, 100, 565, 521);
		
		setBounds(100, 100, 565, 521);
		getContentPane().add(jp);

		JLabel txt_groupName = new JLabel("{name}");
		txt_groupName.setBounds(14, 13, 72, 18);
		jp.add(txt_groupName);

		JLabel txt_groupCode = new JLabel("{code}");
		txt_groupCode.setBounds(14, 42, 72, 18);
		jp.add(txt_groupCode);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 73, 212, 400);
		jp.add(scrollPane);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode(Data.group);// ���ڵ����

		JTree tree_vol = new JTree(top);
		scrollPane.setViewportView(tree_vol);

		// ��ʼ��ȡ���ṹ
		new TreeShowThread(tree_vol, top);

		tree_vol.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				// ��ȡ���Ľڵ�
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_vol.getLastSelectedPathComponent();
				// �ж��Ƿ�Ϊ����
				if (node == null) {
					return;
				}
				DefaultMutableTreeNode parant = (DefaultMutableTreeNode) node.getParent();
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof Volenteer) {
					// Ҷ�ӽڵ�,vol
					// enable btn
					btn_delete.setEnabled(true);
					btn_modify.setEnabled(true);

					Volenteer vol = (Volenteer) nodeInfo;
					// ��ʾ������Ϣ
					showVolInfo(vol, txt_info);
				} else if (nodeInfo instanceof Group) {
					// ���ڵ�,group
					// disable btn
					btn_delete.setEnabled(false);
					btn_modify.setEnabled(false);
					Group group = (Group) nodeInfo;
					// ���ڵ㣬��ʾ��֯��Ϣ
					showGroupInfo(group, txt_info);
				}

			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(238, 13, 308, 420);
		jp.add(scrollPane_1);

		txt_info = new JTextArea();
		txt_info.setEditable(false);
		scrollPane_1.setViewportView(txt_info);

		btn_delete = new JButton("\u5220\u9664");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// delete
				if (JOptionPane.showConfirmDialog(null, "ȷ��ɾ����ɾ�����ɻָ���", "����", JOptionPane.WARNING_MESSAGE,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					// ��ȡ���Ľڵ�
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_vol.getLastSelectedPathComponent();
					// �ж��Ƿ�Ϊ����
					if (node == null) {
						return;
					}
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// Ҷ�ӽڵ�,vol
						Volenteer vol = (Volenteer) nodeInfo;
						Data.group.vol.remove(vol);
						top.remove(node);

						tree_vol.updateUI();
						JOptionPane.showMessageDialog(null, "�ɹ�ɾ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);

					} else if (nodeInfo instanceof Group) {
						// ���ڵ�,group
						JOptionPane.showMessageDialog(null, "���ڵ㲻����ɾ����", "��ʾ", JOptionPane.ERROR_MESSAGE);

					}
				}
			}
		});
		btn_delete.setBounds(432, 446, 113, 27);
		jp.add(btn_delete);

		btn_modify = new JButton("\u66F4\u6539");
		btn_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// modify
				new ModifyInfoUI();
				

			}
		});
		btn_modify.setBounds(240, 446, 113, 27);
		jp.add(btn_modify);

		txt_groupName.setText(Data.groupName);
		txt_groupCode.setText(String.valueOf(Data.groupID));
		
		//first disable btn
		btn_delete.setEnabled(false);
		btn_modify.setEnabled(false);

		tree_vol.expandRow(0);// �׸�չ��
		setVisible(true);

		addWindowListener(new WindowAdapter() {// when closing
			public void windowClosing(WindowEvent e) {
				new MainUI();
			}

		});
	}

	private void showGroupInfo(Group group, JTextArea infoArea) {
		infoArea.setText("");// ��ʼ��
		infoArea.append("��֯���� " + group.name + "\n");
		infoArea.append("��֯ID�� " + group.ID + "\n");
		infoArea.append("��Ա���� " + group.vol.size() + "\n");

	}

	private void showVolInfo(Volenteer vol, JTextArea infoArea) {
		infoArea.setText("");// ��ʼ��
		infoArea.append("������ " + vol.name + "\n");
		infoArea.append("ѧ�ţ� " + vol.IDnum + "\n");
		infoArea.append("�Ա� " + vol.sex + "\n");
		infoArea.append("״̬�� " + vol.state + "\n");
		infoArea.append("�μ���" + vol.activities.size() + "��־Ը��Ŀ\n");
		Activity act = null;
		float time = 0f;// ־Ըʱ��
		int no = 0;
		for (Iterator<Activity> it = vol.activities.iterator(); it.hasNext();) {
			act = it.next();
			time += act.hour;
			infoArea.append(
					(++no) + ".( " + act.getMonth() + "/" + act.getDay() + " ) " + act.name + "-" + act.hour + "Сʱ\n");
		}
		infoArea.append("�ܼ�" + time + "Сʱ");

	}
}

class TreeShowThread extends Thread {
	JTree tree;
	DefaultMutableTreeNode node;

	public TreeShowThread(JTree tree, DefaultMutableTreeNode node) {
		this.tree = tree;
		this.node = node;
		this.start();
	}

	public void run() {
		// create tree
		Volenteer vol = null;
		for (Iterator<Volenteer> it = Data.group.vol.iterator(); it.hasNext();) {
			vol = it.next();
			node.add(new DefaultMutableTreeNode(vol));
		}
		tree.updateUI();

	}
}
