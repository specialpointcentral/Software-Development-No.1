package admin.programUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JList;

public class ShowIndividuActionUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShowIndividuActionUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("\u4FE1\u606F\u67E5\u770B-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setResizable(false);
		setBounds(100, 100, 578, 356);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 13, 168, 295);
		getContentPane().add(scrollPane);
		
		JList list_time = new JList();
		scrollPane.setViewportView(list_time);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(337, 13, 221, 295);
		getContentPane().add(scrollPane_1);
		
		JTextArea txt_info = new JTextArea();
		txt_info.setEditable(false);
		scrollPane_1.setViewportView(txt_info);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(186, 13, 147, 295);
		getContentPane().add(scrollPane_2);
		
		JList list_vol = new JList();
		scrollPane_2.setViewportView(list_vol);
	}
}
