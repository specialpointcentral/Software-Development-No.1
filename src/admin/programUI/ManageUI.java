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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import java.awt.Graphics;

public class ManageUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<Groups> list1;
	private DefaultListModel<Volenteer> list2;
	private JTextArea txt_volSimpleInfo;
	private JButton btn_infoDetail;

	public ManageUI() {
		setBounds(100, 100, 720, 515);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(14, 13, 222, 356);
		add(panel);
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

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("所有组织");
		JTree tree = new JTree(top);
		scrollPane_2.setViewportView(tree);

		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				btn_infoDetail.setEnabled(true);
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (node == null)
					return;
				else {
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// 叶节点,vol
						// 文档部分更新
						txt_volSimpleInfo.setText("");
						txt_volSimpleInfo.append("姓名：" + ((Volenteer) nodeInfo).name + "\n");
						txt_volSimpleInfo.append("学号：" + ((Volenteer) nodeInfo).IDnum + "\n");
						txt_volSimpleInfo.append("性别：" + ((Volenteer) nodeInfo).sex + "\n");
						txt_volSimpleInfo.append("状态：" + ((Volenteer) nodeInfo).state + "\n");
						txt_volSimpleInfo.append("一共参加" + ((Volenteer) nodeInfo).activities.size() + "项志愿活动" + "\n");
						Activity act = null;
						float timecheck = 0f, timeuncheck = 0f;
						int num = 0;
						for (Iterator<Activity> it = ((Volenteer) nodeInfo).activities.iterator(); it.hasNext();) {
							act = it.next();
							if (!act.checked) {// 未被审核的显示
								timeuncheck += act.hour;
								txt_volSimpleInfo.append((++num) + ". | 未检查 | (" + act.getMonth() + "/" + act.getDay()
										+ ") " + act.name + "-时长" + act.hour + "\n");
							} else {
								timecheck += act.hour;
								txt_volSimpleInfo.append((++num) + ". (" + act.getMonth() + "/" + act.getDay() + ") "
										+ act.name + "-时长" + act.hour + "\n");
							}
						}
						txt_volSimpleInfo
								.append("总时长为" + (timecheck + timeuncheck) + "\n其中未被审核的总时长为" + timeuncheck + "小时");
					} else if (nodeInfo instanceof Groups) {
						// 根节点，未读取信息
						new BuildTreeVol(tree, node, ((Groups) nodeInfo).ID);
						// 文档部分更新
						txt_groupInfo.setText("");
						txt_groupInfo.append("组织：" + ((Groups) nodeInfo).name + "\n");
						txt_groupInfo.append("代码：" + ((Groups) nodeInfo).ID + "\n");
						// txt_groupInfo.append("共有" + Data.recentGroup.vol.size() + "个志愿者" + "\n");

					} else if (nodeInfo instanceof Group) {
						// 根节点，已读取信息
						// 文档部分更新
						txt_groupInfo.setText("");
						txt_groupInfo.append("组织：" + ((Group) nodeInfo).name + "\n");
						txt_groupInfo.append("代码：" + ((Group) nodeInfo).ID + "\n");
						txt_groupInfo.append("共有" + ((Group) nodeInfo).vol.size() + "个志愿者" + "\n");
					}
				}
			}
		});

		new ShowGroupTree(top, tree);// 建树

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(239, 13, 283, 356);
		add(panel_1);
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
						// 叶节点,vol
						new IndividuActionChangeUI((Group) ((DefaultMutableTreeNode) node.getParent()).getUserObject(),
								(Volenteer) nodeInfo, false);

					} else
						JOptionPane.showMessageDialog(null, "不允许修改此结点！", "警告", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_2.add(btn_infoModify);

		btn_infoDetail = new JButton("\u8BE6\u7EC6\u4FE1\u606F");
		btn_infoDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				btn_infoDetail.setEnabled(false);
				if (node == null)
					return;
				else {
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// 叶节点,vol
						// 文档部分更新
						txt_volSimpleInfo.append("\n以下是详细信息：\n");
						Activity act = null;
						int num = 0;
						for (Iterator<Activity> it = ((Volenteer) nodeInfo).activities.iterator(); it.hasNext();) {
							act = it.next();
							if (!act.checked) {// 未被审核的显示
								txt_volSimpleInfo.append("----------" + (++num) + "----------" + "\n");
								txt_volSimpleInfo.append("活动名称 " + act.name + "\n");
								txt_volSimpleInfo.append("项目未被审核" + "\n");
								txt_volSimpleInfo.append("志愿时长：" + act.hour + "\n");
								txt_volSimpleInfo.append("活动日期：" + act.getYear() + "年" + act.getMonth() + "月"
										+ act.getDay() + "日" + "\n");
								txt_volSimpleInfo.append("负责组织：" + act.group.name + "\n");
							} else {
								txt_volSimpleInfo.append("----------" + (++num) + "----------" + "\n");
								txt_volSimpleInfo.append("活动名称 " + act.name + "\n");
								txt_volSimpleInfo.append("志愿时长：" + act.hour + "\n");
								txt_volSimpleInfo.append("活动日期：" + act.getYear() + "年" + act.getMonth() + "月"
										+ act.getDay() + "日" + "\n");
								txt_volSimpleInfo.append("负责组织：" + act.group.name + "\n");
							}
						}
					}
				}

			}
		});
		panel_2.add(btn_infoDetail);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);

		txt_volSimpleInfo = new JTextArea();
		txt_volSimpleInfo.setEditable(false);
		scrollPane_1.setViewportView(txt_volSimpleInfo);

		JPanel panel_3 = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(new ImageIcon("1.jpg").getImage(), 0, 0, this);
			}
		};
		panel_3.setBounds(536, 13, 170, 489);
		add(panel_3);

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
