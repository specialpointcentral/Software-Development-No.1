package user.programUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import data.*;
import user.data.Data;
import user.process.WriteData;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class InsertRecordUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_name;
	private JTextField txt_id;
	private JTextField txt_time;
	private JTextField txt_activity;
	private JTextField txt_year;
	private JTextField txt_month;
	private JTextField txt_day;
	private DefaultMutableTreeNode top;
	private JComboBox box_sex;
	private JTree tree;

	private JButton btn_delete;

	public InsertRecordUI() {
		setLayout(null);
		setBounds(0, 0, 575, 480);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 13, 233, 452);
		add(scrollPane);

		top = new DefaultMutableTreeNode(Data.groupName);
		top.setUserObject(Data.group);

		tree = new JTree(top);
		scrollPane.setViewportView(tree);
		new CreateTree(top, tree);// 开始建树

		// 加入监听
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				// 获取树的节点
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				// 判断是否为空树
				if (node == null) {
					return;
				}
				DefaultMutableTreeNode parant = (DefaultMutableTreeNode) node.getParent();
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof Volenteer) {
					// 根节点
					txt_name.setEnabled(false);
					txt_id.setEnabled(false);
					box_sex.setEnabled(false);// 不允许修改
					Volenteer vol = (Volenteer) nodeInfo;
					txt_name.setText(vol.name);
					txt_id.setText(String.valueOf(vol.IDnum));
					box_sex.setSelectedItem(vol.sex);
					txt_activity.setText("");
					txt_time.setText("");
					txt_year.setText("");
					txt_month.setText("");
					txt_day.setText("");
				} else if (nodeInfo instanceof Activity) {
					// 叶子
					txt_name.setEnabled(false);
					txt_id.setEnabled(false);
					box_sex.setEnabled(false);// 不允许修改
					Activity act = (Activity) nodeInfo;
					Volenteer vol = (Volenteer) parant.getUserObject();
					txt_name.setText(vol.name);
					txt_id.setText(String.valueOf(vol.IDnum));
					box_sex.setSelectedItem(vol.sex);
					txt_activity.setText(act.name);
					txt_time.setText(String.valueOf(act.hour));
					txt_year.setText(String.valueOf(act.getYear()));
					txt_month.setText(String.valueOf(act.getMonth()));
					txt_day.setText(String.valueOf(act.getDay()));

				} else if (nodeInfo instanceof Group) {
					// 最根节点
					txt_name.setEnabled(true);
					txt_id.setEnabled(true);
					box_sex.setEnabled(true);// 允许
					txt_name.setText("");
					txt_id.setText("");
					box_sex.setSelectedIndex(0);
					txt_activity.setText("");
					txt_time.setText("");
					txt_year.setText("");
					txt_month.setText("");
					txt_day.setText("");

				}

			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(271, 306, 276, 67);
		add(panel);
		panel.setLayout(null);

		JButton btn_createTable = new JButton("\u751F\u6210\u5199\u5B9E\u8868");
		btn_createTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DateSelectUI();
				
			}
		});
		btn_createTable.setBounds(14, 25, 110, 27);
		panel.add(btn_createTable);

		JButton btn_createData = new JButton("\u6570\u636E\u4E0A\u4F20");
		btn_createData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btn_createData.setText("请等待...");
				new WriteData(Data.groupID,1);
				while(!Data.saveGroup) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				Data.saveGroup=false;
				JOptionPane.showMessageDialog(null, "成功上传！");
				btn_createData.setText("\u6570\u636E\u4E0A\u4F20");
			}
		});
		btn_createData.setBounds(152, 25, 110, 27);
		panel.add(btn_createData);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u63D2\u5165/\u66F4\u6539", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel_2.setBounds(271, 13, 276, 280);
		add(panel_2);
		panel_2.setLayout(null);

		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(14, 35, 72, 18);
		panel_2.add(label);

		JLabel label_1 = new JLabel("\u5B66\u53F7");
		label_1.setBounds(14, 66, 72, 18);
		panel_2.add(label_1);

		JLabel label_2 = new JLabel("\u6D3B\u52A8\u540D\u79F0");
		label_2.setBounds(14, 127, 72, 18);
		panel_2.add(label_2);

		JLabel label_3 = new JLabel("\u5FD7\u613F\u65F6\u957F");
		label_3.setBounds(14, 158, 95, 18);
		panel_2.add(label_3);

		txt_name = new JTextField();
		txt_name.setBounds(87, 32, 168, 24);
		panel_2.add(txt_name);
		txt_name.setColumns(10);

		txt_id = new JTextField();
		txt_id.setBounds(87, 63, 168, 24);
		panel_2.add(txt_id);
		txt_id.setColumns(10);

		txt_time = new JTextField();
		txt_time.setBounds(87, 155, 95, 24);
		panel_2.add(txt_time);
		txt_time.setColumns(10);

		txt_activity = new JTextField();
		txt_activity.setBounds(87, 124, 168, 24);
		panel_2.add(txt_activity);
		txt_activity.setColumns(10);

		JLabel label_5 = new JLabel("\u5C0F\u65F6");
		label_5.setBounds(190, 158, 72, 18);
		panel_2.add(label_5);

		JButton btn_confirm = new JButton("\u66F4\u6539/\u63D2\u5165");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 首先检查是否正确输入
				if (infoCheck()) {

					// 修改删除，若为根节点则插入，若为叶子节点则修改
					// 获取树的节点
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
					// 判断是否为空树
					if (node == null) {

						return;
					}
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// 在根节点位置,vol
						Volenteer vol = (Volenteer) nodeInfo;
						Activity act = new Activity();

						act.group = Data.group;
						act.name = txt_activity.getText();
						act.hour = Integer.valueOf(txt_time.getText());

						act.setYear(Integer.valueOf(txt_year.getText()));
						act.setMonth(Integer.valueOf(txt_month.getText()));
						act.setDay(Integer.valueOf(txt_day.getText()));
						act.checked=false;

						vol.activities.add(act);
						// 由于新加入 需要生成节点

						node.add(new DefaultMutableTreeNode(act));// act

						tree.updateUI();

					} else if (nodeInfo instanceof Activity) {
						// 在叶子节点,act
						Activity act = (Activity) nodeInfo;
						act.group = Data.group;
						act.name = txt_activity.getText();
						act.hour = Float.valueOf(txt_time.getText());

						act.setYear(Integer.valueOf(txt_year.getText()));
						act.setMonth(Integer.valueOf(txt_month.getText()));
						act.setDay(Integer.valueOf(txt_day.getText()));
						act.checked=false;

						tree.updateUI();
					} else if (nodeInfo instanceof Group) {
						// 最根节点,root
						Group group = (Group) nodeInfo;

						Volenteer vol = new Volenteer();
						vol.name = txt_name.getText();
						vol.IDnum = Integer.valueOf(txt_id.getText());
						vol.sex = box_sex.getSelectedItem().toString();

						Activity act = new Activity();

						act.group = Data.group;
						act.name = txt_activity.getText();
						act.hour = Integer.valueOf(txt_time.getText());

						act.setYear(Integer.valueOf(txt_year.getText()));
						act.setMonth(Integer.valueOf(txt_month.getText()));
						act.setDay(Integer.valueOf(txt_day.getText()));
						act.checked=false;

						vol.activities.add(act);

						group.vol.add(vol);

						// 由于新加入 需要生成节点
						DefaultMutableTreeNode nodes = new DefaultMutableTreeNode(vol);// 根节点,vol
						DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(act);// 叶子节点,act

						nodes.add(leaf);
						node.add(nodes);

						tree.updateUI();
					}
				}
			}
		});
		btn_confirm.setBounds(148, 240, 113, 27);
		panel_2.add(btn_confirm);

		JLabel label_4 = new JLabel("\u65F6\u95F4");
		label_4.setBounds(14, 189, 95, 18);
		panel_2.add(label_4);

		JLabel label_6 = new JLabel("\u5E74");
		label_6.setBounds(138, 192, 22, 18);
		panel_2.add(label_6);

		txt_year = new JTextField();
		txt_year.setBounds(87, 189, 48, 24);
		panel_2.add(txt_year);
		txt_year.setColumns(10);

		txt_month = new JTextField();
		txt_month.setBounds(159, 189, 36, 24);
		panel_2.add(txt_month);
		txt_month.setColumns(10);

		txt_day = new JTextField();
		txt_day.setBounds(215, 189, 36, 24);
		panel_2.add(txt_day);
		txt_day.setColumns(10);

		JLabel label_7 = new JLabel("\u6708");
		label_7.setBounds(195, 192, 15, 18);
		panel_2.add(label_7);

		JLabel label_8 = new JLabel("\u65E5");
		label_8.setBounds(253, 192, 15, 18);
		panel_2.add(label_8);

		btn_delete = new JButton("\u5220\u9664");
		btn_delete.setBounds(14, 240, 110, 27);
		panel_2.add(btn_delete);

		JLabel label_9 = new JLabel("\u6027\u522B");
		label_9.setBounds(14, 96, 72, 18);
		panel_2.add(label_9);

		box_sex = new JComboBox();
		box_sex.setModel(new DefaultComboBoxModel(new String[] { "\u7537", "\u5973" }));
		box_sex.setSelectedIndex(0);
		box_sex.setBounds(87, 93, 73, 24);
		panel_2.add(box_sex);

		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "确认删除？", "警告", JOptionPane.WARNING_MESSAGE,
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					// 修改删除，若为根节点则插入，若为叶子节点则修改
					// 获取树的节点
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
					// 判断是否为空树
					if (node == null) {
						return;
					}
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// 在根节点位置,vol
						Volenteer vol = (Volenteer) nodeInfo;
						Data.group.vol.remove(vol);

						top.remove(node);
						tree.updateUI();
						JOptionPane.showMessageDialog(null, "已删除");

					} else if (nodeInfo instanceof Activity) {
						// 在叶子节点,act
						Activity act = (Activity) nodeInfo;
						// 父节点
						DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();

						Volenteer vol = (Volenteer) parent.getUserObject();
						vol.activities.remove(act);
						parent.remove(node);
						tree.updateUI();
						JOptionPane.showMessageDialog(null, "已删除");

					} else if (nodeInfo instanceof Group) {
						// 最根节点,root
						JOptionPane.showMessageDialog(null, "根节点不能删除");
					}
				}
			}
		});
		tree.expandRow(0);// 首个展开

	}

	/**
	 * check information
	 * 
	 * @return true marked information is checked;false marked information is not
	 *         correct
	 */
	public boolean infoCheck() {
		// 不符合条件的
		try {
			if (txt_name.getText().trim().equals("")) {
				// name is incorrect
				JOptionPane.showMessageDialog(null, "姓名不能为空！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (txt_id.getText().trim().equals("") || Integer.valueOf(txt_id.getText()) <= 10000000
					|| Integer.valueOf(txt_id.getText()) >= 1000000000) {
				// id is incorrect
				JOptionPane.showMessageDialog(null, "学号格式不正确！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (txt_activity.getText().trim().equals("")) {
				// activity is incorrect
				JOptionPane.showMessageDialog(null, "活动名称不能为空！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (txt_time.getText().trim().equals("") || Float.valueOf(txt_time.getText()) < 0f
					|| Float.valueOf(txt_time.getText()) > 24f) {
				// time is incorrect
				JOptionPane.showMessageDialog(null, "志愿时长不正确！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (txt_year.getText().trim().equals("") || Integer.valueOf(txt_year.getText()) < 1000
					|| Integer.valueOf(txt_year.getText()) >= 10000) {
				// year is incorrect
				JOptionPane.showMessageDialog(null, "年不正确！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (txt_month.getText().trim().equals("") || Integer.valueOf(txt_month.getText()) < 0
					|| Integer.valueOf(txt_month.getText()) > 12) {
				// month is incorrect
				JOptionPane.showMessageDialog(null, "月不正确！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (txt_day.getText().trim().equals("") || Integer.valueOf(txt_day.getText()) < 0
					|| Integer.valueOf(txt_day.getText()) > 31) {
				// day is incorrect
				JOptionPane.showMessageDialog(null, "日不正确！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else
				return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "输入有误，请检查！", "提示", JOptionPane.ERROR_MESSAGE);
			return false;

		}
	}
}

class CreateTree extends Thread {
	DefaultMutableTreeNode jt;
	JTree tree;

	public CreateTree(DefaultMutableTreeNode jt, JTree tree) {
		this.jt = jt;
		this.tree = tree;
		this.start();
	}

	public void run() {
		// 更新信息
		Volenteer vols;
		for (Iterator<Volenteer> vol = Data.group.vol.iterator(); vol.hasNext();) {
			vols = vol.next();
			// 加入学生节点
			DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(vols);
			jt.add(node1);
			Activity act;
			for (Iterator<Activity> activity = vols.activities.iterator(); activity.hasNext();) {
				act = activity.next();
				DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(act);
				node1.add(node2);
			}

		}
		// tree.setSelectionPath(new TreePath(jt.getPath()));
		tree.updateUI();

	}

}
