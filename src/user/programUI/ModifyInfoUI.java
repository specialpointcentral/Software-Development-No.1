package user.programUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import data.*;
import user.data.Data;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifyInfoUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_name;
	private JTextField txt_IDNumber;
	private JTree tree_info;
	private JComboBox cb_state;

	public ModifyInfoUI() {
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\u4FE1\u606F\u4FEE\u6539-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setBounds(100, 100, 483, 355);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(14, 13, 139, 294);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(14, 13, 72, 18);
		panel.add(label);

		JLabel label_1 = new JLabel("\u5B66\u53F7");
		label_1.setBounds(14, 72, 72, 18);
		panel.add(label_1);

		JLabel label_2 = new JLabel("\u6027\u522B");
		label_2.setBounds(14, 129, 72, 18);
		panel.add(label_2);

		JLabel label_3 = new JLabel("\u72B6\u6001");
		label_3.setBounds(14, 186, 72, 18);
		panel.add(label_3);

		txt_name = new JTextField();
		txt_name.setBounds(14, 35, 111, 24);
		panel.add(txt_name);
		txt_name.setColumns(10);

		txt_IDNumber = new JTextField();
		txt_IDNumber.setBounds(14, 92, 111, 24);
		panel.add(txt_IDNumber);
		txt_IDNumber.setColumns(10);

		JComboBox cb_sex = new JComboBox();
		cb_sex.setModel(new DefaultComboBoxModel(new String[] { "\u7537", "\u5973" }));
		cb_sex.setBounds(14, 149, 72, 24);
		panel.add(cb_sex);

		cb_state = new JComboBox();
		cb_state.setEditable(true);
		cb_state.setModel(new DefaultComboBoxModel(
				new String[] { "\u5728\u804C", "\u8BD5\u7528", "\u4E34\u65F6", "\u975E\u5728\u804C" }));
		cb_state.setBounds(14, 217, 111, 24);
		panel.add(cb_state);

		JButton btn_modify = new JButton("\u4FEE\u6539");
		btn_modify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// check!
				if (checkInfo()) {
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_info.getLastSelectedPathComponent();

					// 判断是否为空树
					if (node == null) {
						return;
					}
					Object nodeInfo = node.getUserObject();
					if (nodeInfo instanceof Volenteer) {
						// 叶节点,vol
						Volenteer vol = (Volenteer) nodeInfo;
						vol.name = txt_name.getText();
						vol.IDnum = Integer.valueOf(txt_IDNumber.getText());
						vol.sex = cb_sex.getSelectedItem().toString();
						vol.state = cb_state.getSelectedItem().toString();
						
						JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);

					} else
						return;
				}
			}
		});
		btn_modify.setBounds(44, 254, 81, 27);
		panel.add(btn_modify);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 13, 311, 266);
		getContentPane().add(scrollPane);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode(Data.group);
		tree_info = new JTree(top);

		new TreeShowThread(tree_info, top);
		scrollPane.setViewportView(tree_info);

		tree_info.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree_info.getLastSelectedPathComponent();

				// 判断是否为空树
				if (node == null) {
					return;
				}
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof Volenteer) {
					// 叶节点,vol
					btn_modify.setEnabled(true);
					Volenteer vol = (Volenteer) nodeInfo;
					txt_name.setText(vol.name);
					txt_IDNumber.setText(String.valueOf(vol.IDnum));
					cb_sex.setSelectedItem(vol.sex);
					cb_state.setSelectedItem(vol.state);
				} else if (nodeInfo instanceof Group) {
					// 根节点,group
					btn_modify.setEnabled(false);
					txt_name.setText("");
					txt_IDNumber.setText("");
					cb_sex.setSelectedItem("男");
					cb_state.setSelectedIndex(0);
				}
			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(166, 285, 297, 25);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel label_4 = new JLabel("\u70B9\u51FB\u9879\u76EE\u8FDB\u884C\u4FEE\u6539");
		label_4.setBounds(93, 4, 104, 16);
		label_4.setFont(new Font("新宋体", Font.PLAIN, 13));
		panel_1.add(label_4);
		// group展开
		tree_info.expandRow(0);
		// first make btn disable
		btn_modify.setEnabled(false);

		setVisible(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}

		});
	}

	public ModifyInfoUI(Object object) {
		this();
		// find object

	}

	public boolean checkInfo() {
		try {
			if (txt_name.getText().trim().equals("")) {
				// name is incorrect
				JOptionPane.showMessageDialog(null, "姓名不能为空！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (txt_IDNumber.getText().trim().equals("") || Integer.valueOf(txt_IDNumber.getText()) <= 10000000
					|| Integer.valueOf(txt_IDNumber.getText()) >= 1000000000) {
				// id is incorrect
				JOptionPane.showMessageDialog(null, "学号格式不正确！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (cb_state.getSelectedItem().toString().trim().equals("")) {
				// state is incorrect
				JOptionPane.showMessageDialog(null, "状态不正确！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else
				return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "输入有误，请检查！", "提示", JOptionPane.ERROR_MESSAGE);
			return false;

		}

	}
}

class TreeThread extends Thread {
	DefaultMutableTreeNode jt;
	JTree tree;

	public TreeThread(DefaultMutableTreeNode jt, JTree tree) {
		this.jt = jt;
		this.tree = tree;
		this.start();
	}

	public void run() {
		// 更新信息
		Volenteer vols;
		for (Iterator<Volenteer> vol = Data.group.vol.iterator(); vol.hasNext();) {
			vols = vol.next();
			// 加入志愿者节点
			jt.add(new DefaultMutableTreeNode(vols));
		}
		// tree.setSelectionPath(new TreePath(jt.getPath()));
		tree.updateUI();

	}

}