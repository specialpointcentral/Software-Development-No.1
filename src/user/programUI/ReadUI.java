package user.programUI;

import javax.swing.JFrame;
import javax.swing.JLabel;

import user.data.Data;

public class ReadUI extends JFrame {
	public ReadUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\u8BFB\u53D6\u6587\u4EF6-\u5FD7\u613F\u8005\u7BA1\u7406\u7CFB\u7EDF");
		setBounds(100, 100, 308, 105);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8BFB\u53D6\u6587\u4EF6\u4E2D...");
		label.setBounds(14, 23, 118, 18);
		getContentPane().add(label);
		setVisible(true);
		
		new ReadTread(this);
	}
}
class ReadTread extends Thread{
	JFrame jf;
	public ReadTread(JFrame jf) {
		this.jf=jf;
		this.start();
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(Data.Groupready) {
				//group read ready
				jf.dispose();
			}
		}
	}
}