package admin.programUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import data.*;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class IndividuActionChangeUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_actionName;
	private JTextField txt_actionTime;
	private JTextField txt_year;
	private JTextField txt_month;
	private JTextField txt_day;

	Group group;
	Volenteer vol;
	boolean showUnChecked;// 模式 只显示未被检查的

	public IndividuActionChangeUI(Group group, Volenteer vol, boolean showUnChecked) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		this.group = group;
		this.vol = vol;
		this.showUnChecked = showUnChecked;
		setTitle("\u4FEE\u6539-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setBounds(100, 100, 474, 442);
		getContentPane().setLayout(null);

		JLabel txt_infomation = new JLabel(
				"\u6B63\u5728\u5BF9{name}\u5728{groupName}\u7684\u4FE1\u606F\u8FDB\u884C\u4FEE\u6539");
		txt_infomation.setBounds(14, 13, 360, 18);
		getContentPane().add(txt_infomation);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 43, 187, 318);
		getContentPane().add(scrollPane);

		DefaultListModel<Activity> l = new DefaultListModel<Activity>();

		JList<Activity> list = new JList<Activity>(l);
		scrollPane.setViewportView(list);
		new CreatListThread(vol, l, showUnChecked);

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					Activity act = list.getSelectedValue();
					txt_actionName.setText(act.name);
					txt_actionTime.setText(String.valueOf(act.hour));
					txt_year.setText(String.valueOf(act.getYear()));
					txt_month.setText(String.valueOf(act.getMonth()));
					txt_day.setText(String.valueOf(act.getDay()));
				}

			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(215, 44, 239, 317);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("\u6D3B\u52A8\u540D\u79F0");
		label.setBounds(14, 13, 72, 18);
		panel.add(label);

		JLabel label_1 = new JLabel("\u5FD7\u613F\u65F6\u957F");
		label_1.setBounds(14, 62, 72, 18);
		panel.add(label_1);

		JButton btn_submit = new JButton("\u63D0\u4EA4");
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// submit
				if (insertInfoCheck()) {
					Activity act = list.getSelectedValue();
					act.name = txt_actionName.getText();
					act.hour = Float.valueOf(txt_actionTime.getText());
					act.setYear(Integer.valueOf(txt_year.getText()));
					act.setMonth(Integer.valueOf(txt_month.getText()));
					act.setDay(Integer.valueOf(txt_day.getText()));
				}
			}
		});
		btn_submit.setBounds(112, 277, 113, 27);
		panel.add(btn_submit);

		txt_actionName = new JTextField();
		txt_actionName.setBounds(100, 10, 125, 24);
		panel.add(txt_actionName);
		txt_actionName.setColumns(10);

		txt_actionTime = new JTextField();
		txt_actionTime.setBounds(100, 59, 125, 24);
		panel.add(txt_actionTime);
		txt_actionTime.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 128, 211, 35);
		panel.add(panel_1);
		panel_1.setLayout(null);

		txt_year = new JTextField();
		txt_year.setBounds(3, 6, 61, 24);
		txt_year.setColumns(10);
		panel_1.add(txt_year);

		JLabel label_2 = new JLabel("\u5E74");
		label_2.setBounds(66, 9, 15, 18);
		panel_1.add(label_2);

		txt_month = new JTextField();
		txt_month.setBounds(82, 6, 43, 24);
		panel_1.add(txt_month);
		txt_month.setColumns(10);

		txt_day = new JTextField();
		txt_day.setBounds(142, 6, 42, 24);
		panel_1.add(txt_day);
		txt_day.setColumns(10);

		JLabel label_3 = new JLabel("\u6708");
		label_3.setBounds(125, 9, 15, 18);
		panel_1.add(label_3);

		JLabel label_4 = new JLabel("\u65E5");
		label_4.setBounds(185, 9, 15, 18);
		panel_1.add(label_4);

		JLabel label_5 = new JLabel("\u6D3B\u52A8\u65F6\u95F4");
		label_5.setBounds(14, 103, 72, 18);
		panel.add(label_5);

		JButton btn_confirm = new JButton("\u786E\u8BA4");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_confirm.setBounds(363, 367, 91, 27);
		getContentPane().add(btn_confirm);

		txt_infomation.setText("正在对 " + vol.name + " 在 " + group.name + " 的信息进行修改");
		setVisible(true);

	}

	public boolean insertInfoCheck() {
		// action name
		try {
			if (txt_actionName.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "活动名称不能为空！", "提示", JOptionPane.ERROR_MESSAGE);
				return false;
			} else if (txt_actionTime.getText().trim().equals("") || Float.valueOf(txt_actionTime.getText()) < 0f
					|| Float.valueOf(txt_actionTime.getText()) > 24f) {
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
			JOptionPane.showMessageDialog(null, "输入有误！", "提示", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}

class CreatListThread extends Thread {
	Volenteer vol;
	DefaultListModel<Activity> list;
	boolean showUnChecked;

	public CreatListThread(Volenteer vol, DefaultListModel<Activity> list, boolean showUnChecked) {
		this.vol = vol;
		this.list = list;
		this.start();
	}

	public void run() {
		Activity act = null;
		for (Iterator<Activity> it = vol.activities.iterator(); it.hasNext();) {
			act = it.next();
			if (!act.checked || !showUnChecked) {
				list.addElement(act);
			}

		}
	}
}