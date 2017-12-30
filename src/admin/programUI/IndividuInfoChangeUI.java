package admin.programUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IndividuInfoChangeUI extends JFrame{
	private JTextField txt_name;
	private JTextField txt_idNumber;
	public IndividuInfoChangeUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("\u4FE1\u606F\u4FEE\u6539-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setBounds(100, 100, 270, 226);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "\u4E2A\u4EBA\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(14, 13, 235, 131);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(14, 27, 72, 18);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5B66\u53F7");
		label_1.setBounds(14, 58, 72, 18);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u6027\u522B");
		label_2.setBounds(14, 89, 72, 18);
		panel.add(label_2);
		
		JComboBox com_sex = new JComboBox();
		com_sex.setModel(new DefaultComboBoxModel(new String[] {"\u7537", "\u5973"}));
		com_sex.setBounds(66, 86, 72, 24);
		panel.add(com_sex);
		
		txt_name = new JTextField();
		txt_name.setBounds(66, 24, 155, 24);
		panel.add(txt_name);
		txt_name.setColumns(10);
		
		txt_idNumber = new JTextField();
		txt_idNumber.setBounds(66, 55, 155, 24);
		panel.add(txt_idNumber);
		txt_idNumber.setColumns(10);
		
		JButton btn_confirm = new JButton("\u786E\u8BA4");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btn_confirm.setBounds(136, 151, 113, 27);
		getContentPane().add(btn_confirm);
		
		setVisible(true);
	}
}
