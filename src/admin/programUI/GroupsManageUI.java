package admin.programUI;

import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import admin.data.Data;
import data.Groups;
import data.User;
import admin.programUI.MainUI;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class GroupsManageUI extends JFrame {
	private JTextField txt_name;
	private JTextField txt_ID;
	private JTextField txt_count;
	private JPasswordField psw_psw;
	private JPasswordField psw_confirm;
	private JButton btn_delete;
	private JButton btn_modify;

	public GroupsManageUI() {
		setTitle("\u7EC4\u7EC7\u7BA1\u7406-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 556, 405);
		setResizable(false);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 254, 344);
		getContentPane().add(scrollPane);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("组织");

		JTree tree_group = new JTree(top);
		scrollPane.setViewportView(tree_group);
		new BuildTree(tree_group, top);
		tree_group.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				// TODO Auto-generated method stub
				// 获取树的节点
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_group.getLastSelectedPathComponent();
				// 判断是否为空树
				if (node == null) {
					return;
				}
				DefaultMutableTreeNode parant = (DefaultMutableTreeNode) node.getParent();
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof User) {
					btn_delete.setEnabled(true);
					User user = (User) nodeInfo;

					txt_name.setText(((Groups) parant.getUserObject()).name);
					txt_ID.setText(String.valueOf(((Groups) parant.getUserObject()).ID));
					txt_count.setText(user.getUser());
					psw_psw.setText("");
					psw_confirm.setText("");

				} else if (nodeInfo instanceof Groups) {
					btn_delete.setEnabled(true);
					Groups groups = (Groups) nodeInfo;
					txt_name.setText(groups.name);
					txt_ID.setText(String.valueOf(groups.ID));
					txt_count.setText("");
					psw_psw.setText("");
					psw_confirm.setText("");

				} else {
					btn_delete.setEnabled(false);
					txt_name.setText("");
					txt_ID.setText("");
					txt_name.setText("");
					psw_psw.setText("");
					psw_confirm.setText("");

				}
			}
		});

		JPanel panel = new JPanel();
		panel.setBounds(282, 13, 254, 344);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\u7EC4\u7EC7\u540D\u79F0");
		label.setBounds(14, 13, 72, 18);
		panel.add(label);

		JLabel label_1 = new JLabel("\u7EC4\u7EC7\u4EE3\u7801");
		label_1.setBounds(14, 71, 72, 18);
		panel.add(label_1);

		JLabel label_2 = new JLabel("\u7BA1\u7406\u5458\u8D26\u53F7");
		label_2.setBounds(14, 127, 83, 18);
		panel.add(label_2);

		txt_name = new JTextField();
		txt_name.setBounds(14, 34, 214, 24);
		panel.add(txt_name);
		txt_name.setColumns(10);

		txt_ID = new JTextField();
		txt_ID.setBounds(14, 90, 214, 24);
		panel.add(txt_ID);
		txt_ID.setColumns(10);

		txt_count = new JTextField();
		txt_count.setBounds(14, 147, 214, 24);
		panel.add(txt_count);
		txt_count.setColumns(10);

		JLabel label_3 = new JLabel("\u7BA1\u7406\u5458\u5BC6\u7801");
		label_3.setBounds(14, 182, 83, 18);
		panel.add(label_3);

		psw_psw = new JPasswordField();
		psw_psw.setBounds(14, 207, 214, 24);
		panel.add(psw_psw);

		JLabel label_4 = new JLabel("\u518D\u6B21\u8F93\u5165");
		label_4.setBounds(14, 244, 72, 18);
		panel.add(label_4);

		psw_confirm = new JPasswordField();
		psw_confirm.setBounds(14, 264, 214, 24);
		panel.add(psw_confirm);

		btn_delete = new JButton("\u5220\u9664");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取树的节点
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_group.getLastSelectedPathComponent();
				// 判断是否为空树
				if (node == null) {
					return;
				}
				DefaultMutableTreeNode parant = (DefaultMutableTreeNode) node.getParent();
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof User) {
					((Groups) parant.getUserObject()).users.remove(nodeInfo);
					parant.remove(node);
					tree_group.updateUI();
					JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

				} else if (nodeInfo instanceof Groups) {
					Data.groups.remove(nodeInfo);
					parant.remove(node);
					tree_group.updateUI();
					JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

				} else {
					JOptionPane.showMessageDialog(null, "不允许删除！", "提示", JOptionPane.WARNING_MESSAGE);

				}
			}
		});
		btn_delete.setBounds(14, 304, 83, 27);
		panel.add(btn_delete);

		btn_modify = new JButton("\u66F4\u6539/\u6DFB\u52A0");
		btn_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 获取树的节点
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_group.getLastSelectedPathComponent();
				// 判断是否为空树
				if (node == null) {
					return;
				}
				DefaultMutableTreeNode parant = (DefaultMutableTreeNode) node.getParent();
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof User) {
					if (infocheck(true)) {
						User user = (User) nodeInfo;
						user.setUser(txt_name.getText());
						user.setPsw(new String(psw_psw.getPassword()));
						tree_group.updateUI();
						JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (nodeInfo instanceof Groups) {
					if (txt_count.getText().trim().equals("")) {// 修改
						if (infocheck(false)) {
							((Groups) nodeInfo).name = txt_name.getText();
							((Groups) nodeInfo).ID = Integer.valueOf(txt_ID.getText());
							tree_group.updateUI();
							JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
							
						}
					} else {// 添加
						if (infocheck(true)) {

							User user = new User();
							user.setUser(txt_count.getText());
							user.setPsw(new String(psw_psw.getPassword()));
							((Groups) nodeInfo).users.add(user);
							
							DefaultMutableTreeNode nodes = new DefaultMutableTreeNode(user);

							node.add(nodes);
							tree_group.updateUI();
							JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
					}

				} else {// 添加组织
					if (infocheck(true)) {
						Groups groups = new Groups();
						groups.name = txt_name.getText();
						groups.ID = Integer.valueOf(txt_ID.getText());
						DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(groups);
						User user = new User();
						user.setUser(txt_count.getText());
						user.setPsw(new String(psw_psw.getPassword()));
						groups.users.add(user);
						DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(user);
						node1.add(node2);
						node.add(node1);
						Data.groups.add(groups);
						tree_group.updateUI();

						JOptionPane.showMessageDialog(null, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});
		btn_modify.setBounds(115, 304, 113, 27);
		panel.add(btn_modify);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new MainUI();
				dispose();
			}

		});
	}

	public boolean infocheck(boolean checkall) {
		// checkall mark check user and psw
		String psw = new String(psw_psw.getPassword());
		String psw_confirms = new String(psw_confirm.getPassword());
		if (checkall) {
			if (txt_name.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "组织名称错误！", "提示", JOptionPane.WARNING_MESSAGE);
				return false;
			} else if (txt_ID.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "组织ID错误！", "提示", JOptionPane.WARNING_MESSAGE);
				return false;
			} else if (txt_count.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "用户名错误！", "提示", JOptionPane.WARNING_MESSAGE);
				return false;
			} else if (!psw_confirms.equals(psw)) {
				JOptionPane.showMessageDialog(null, "两次密码不一致！", "提示", JOptionPane.WARNING_MESSAGE);
				return false;
			} else
				return true;
		} else {
			if (txt_name.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "组织名称错误！", "提示", JOptionPane.WARNING_MESSAGE);
				return false;
			} else if (txt_ID.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "组织ID错误！", "提示", JOptionPane.WARNING_MESSAGE);
				return false;
			} else
				return true;
		}

	}
}

class BuildTree extends Thread {
	JTree jt;
	DefaultMutableTreeNode node;

	public BuildTree(JTree jt, DefaultMutableTreeNode node) {
		this.jt = jt;
		this.node = node;
		this.start();
	}

	public void run() {
		Groups groups = null;
		for (Iterator<Groups> it = Data.groups.iterator(); it.hasNext();) {
			groups = it.next();
			DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(groups);
			node.add(node1);
			User user = null;
			for (Iterator<User> its = groups.users.iterator(); its.hasNext();) {
				user = its.next();
				DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(user);
				node1.add(node2);
			}
		}
		jt.updateUI();
	}
}