package admin.programUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;

public class DetailUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	public DetailUI() {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		setTitle("\u8BE6\u7EC6\u4FE1\u606F-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 602, 522);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(14, 13, 230, 163);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JButton btn_modify = new JButton("\u4FE1\u606F\u4FEE\u6539");
		panel_4.add(btn_modify, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea txt_personalInfo = new JTextArea();
		txt_personalInfo.setEditable(false);
		scrollPane.setViewportView(txt_personalInfo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(258, 13, 312, 163);
		getContentPane().add(scrollPane_1);
		
		JTextArea txt_recentAction = new JTextArea();
		txt_recentAction.setEditable(false);
		scrollPane_1.setViewportView(txt_recentAction);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(14, 189, 556, 273);
		getContentPane().add(scrollPane_2);
		
		JTextArea txt_detailInfo = new JTextArea();
		txt_detailInfo.setEditable(false);
		scrollPane_2.setViewportView(txt_detailInfo);
		
		setResizable(false);//不可放大
		setVisible(true);
	}
}
