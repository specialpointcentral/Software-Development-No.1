package admin.programUI;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import admin.data.Data;
import admin.process.WriteData;

import javax.swing.JLabel;
import java.awt.Color;

public class SaveUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
	public SaveUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 127);
		setResizable(false);
		setTitle("\u4FE1\u606F\u6B63\u5728\u4FDD\u5B58-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		getContentPane().setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(14, 48, 404, 23);
		getContentPane().add(progressBar);

		JLabel label = new JLabel("\u914D\u7F6E\u6587\u4EF6\u4EE5\u53CA\u4FE1\u606F\u6B63\u5728\u4FDD\u5B58...");
		label.setBounds(14, 13, 212, 18);
		getContentPane().add(label);

		setVisible(true);
		new Saving(progressBar);		
	}
	
}

class Saving extends Thread{
	JProgressBar progressBar;
	public Saving(JProgressBar progressBar) {
		this.progressBar=progressBar;
		this.start();
	}
	public void run() {
		new WriteData(1,Data.readGroup);
		new WriteData(2,Data.groups);
		new ProgressBar(progressBar);
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (Data.GroupSaveOK && Data.GroupsSaveOK) {// 完成写入
				progressBar.setValue(progressBar.getMaximum());
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}

		// Saving is OK
		 System.exit(0);//结束所有
	}
}

class ProgressBar {
	public ProgressBar(JProgressBar bar) {
		for (int i = bar.getMinimum(); i <= bar.getMaximum() - 5; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bar.setValue(i);
		}
	}
}
