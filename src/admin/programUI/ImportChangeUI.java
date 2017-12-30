package admin.programUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImportChangeUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txt_name;
	private JTextField txt_idNumber;
	private JTextField txt_activeName;
	private JTextField txt_volTime;
	public ImportChangeUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setResizable(false);
		setTitle("\u7F16\u8F91-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 323, 227);
		JPanel jp=new JPanel();
		setContentPane(jp);
		jp.setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(14, 13, 72, 18);
		jp.add(label);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u53F7");
		lblNewLabel.setBounds(14, 44, 72, 18);
		jp.add(lblNewLabel);
		
		JLabel label_1 = new JLabel("\u6D3B\u52A8\u540D\u79F0");
		label_1.setBounds(14, 75, 72, 18);
		jp.add(label_1);
		
		JLabel label_2 = new JLabel("\u5FD7\u613F\u65F6\u957F");
		label_2.setBounds(14, 106, 72, 18);
		jp.add(label_2);
		
		txt_name = new JTextField();
		txt_name.setBounds(100, 10, 194, 24);
		jp.add(txt_name);
		txt_name.setColumns(10);
		
		txt_idNumber = new JTextField();
		txt_idNumber.setColumns(10);
		txt_idNumber.setBounds(100, 41, 194, 24);
		jp.add(txt_idNumber);
		
		txt_activeName = new JTextField();
		txt_activeName.setColumns(10);
		txt_activeName.setBounds(100, 72, 194, 24);
		jp.add(txt_activeName);
		
		txt_volTime = new JTextField();
		txt_volTime.setColumns(10);
		txt_volTime.setBounds(100, 103, 194, 24);
		jp.add(txt_volTime);
		
		JButton btn_submit = new JButton("\u63D0\u4EA4");
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btn_submit.setBounds(214, 143, 80, 27);
		jp.add(btn_submit);
		setVisible(true);
		
	}
}
