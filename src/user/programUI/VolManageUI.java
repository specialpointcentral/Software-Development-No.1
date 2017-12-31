package user.programUI;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import data.*;
import user.data.Data;

import javax.swing.*;

import java.util.Iterator;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VolManageUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextArea txt_info;
	private JButton btn_modify;
	private JButton btn_delete;

	public VolManageUI() {
		
		 setLayout(null);
		 setBounds(100, 100, 565, 498);
		
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 212, 460);
		 add(scrollPane);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode(Data.group);// 根节点加入

		JTree tree_vol = new JTree(top);
		scrollPane.setViewportView(tree_vol);

		// 开始读取树结构
		new TreeShowThread(tree_vol, top);

		tree_vol.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				// 获取树的节点
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_vol.getLastSelectedPathComponent();
				// 判断是否为空树
				if (node == null) {
					return;
				}
				DefaultMutableTreeNode parant = (DefaultMutableTreeNode) node.getParent();
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof Volenteer) {
					// 叶子节点,vol
					// enable btn
					btn_delete.setEnabled(true);
					btn_modify.setEnabled(true);

					Volenteer vol = (Volenteer) nodeInfo;
					// 显示个人信息
					showVolInfo(vol, txt_info);
				} else if (nodeInfo instanceof Group) {
					// 根节点,group
					// disable btn
					btn_delete.setEnabled(false);
					btn_modify.setEnabled(false);
					Group group = (Group) nodeInfo;
					// 根节点，显示组织信息
					showGroupInfo(group, txt_info);
				}

			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(238, 13, 308, 420);
		 add(scrollPane_1);

		txt_info = new JTextArea();
		txt_info.setEditable(false);
		scrollPane_1.setViewportView(txt_info);

		btn_delete = new JButton("\u5220\u9664");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// delete
				if (JOptionPane.showConfirmDialog(null, "确认删除？删除不可恢复！", "警告", JOptionPane.WARNING_MESSAGE,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					// 获取树的节点
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_vol.getLastSelectedPathComponent();
					// 判断是否为空树
					if (node == null) {
						return;
					}
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// 叶子节点,vol
						Volenteer vol = (Volenteer) nodeInfo;
						Data.group.vol.remove(vol);
						top.remove(node);

						tree_vol.updateUI();
						JOptionPane.showMessageDialog(null, "成功删除", "提示", JOptionPane.INFORMATION_MESSAGE);

					} else if (nodeInfo instanceof Group) {
						// 根节点,group
						JOptionPane.showMessageDialog(null, "根节点不允许删除！", "提示", JOptionPane.ERROR_MESSAGE);

					}
				}
			}
		});
		btn_delete.setBounds(432, 446, 113, 27);
		 add(btn_delete);

		btn_modify = new JButton("\u66F4\u6539\u4E2A\u4EBA\u4FE1\u606F");
		btn_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// modify
				new ModifyInfoUI();
				

			}
		});
		btn_modify.setBounds(240, 446, 131, 27);
		 add(btn_modify);
		
		//first disable btn
		btn_delete.setEnabled(false);
		btn_modify.setEnabled(false);

		tree_vol.expandRow(0);// 首个展开
		
	}

	private void showGroupInfo(Group group, JTextArea infoArea) {
		infoArea.setText("");// 初始化
		infoArea.append("组织名： " + group.name + "\n");
		infoArea.append("组织ID： " + group.ID + "\n");
		infoArea.append("成员数： " + group.vol.size() + "\n");

	}

	private void showVolInfo(Volenteer vol, JTextArea infoArea) {
		infoArea.setText("");// 初始化
		infoArea.append("姓名： " + vol.name + "\n");
		infoArea.append("学号： " + vol.IDnum + "\n");
		infoArea.append("性别： " + vol.sex + "\n");
		infoArea.append("状态： " + vol.state + "\n");
		infoArea.append("参加了" + vol.activities.size() + "个志愿项目\n");
		Activity act = null;
		float time = 0f;// 志愿时长
		int no = 0;
		for (Iterator<Activity> it = vol.activities.iterator(); it.hasNext();) {
			act = it.next();
			time += act.hour;
			infoArea.append(
					(++no) + ".( " + act.getMonth() + "/" + act.getDay() + " ) " + act.name + "-" + act.hour + "小时\n");
		}
		infoArea.append("总计" + time + "小时");

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
