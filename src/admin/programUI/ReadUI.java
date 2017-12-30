package admin.programUI;

import javax.swing.JFrame;
import javax.swing.JLabel;

import admin.data.Data;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ReadUI extends JFrame{
	public ReadUI() {
		setTitle("\u4FE1\u606F\u8BFB\u53D6");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 265, 98);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6B63\u5728\u8BFB\u53D6\u4FE1\u606F...");
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 17));
		label.setBounds(14, 25, 159, 18);
		getContentPane().add(label);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Data.recentReadReady=false;
				dispose();
			}
		});
	}
	
}
