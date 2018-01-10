package user.programUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import user.data.Data;
import data.Activity;
import data.Volenteer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class DateSelectUI extends JFrame {
	private JTextField year1;
	private JTextField month1;
	private JTextField day1;
	private JTextField year2;
	private JTextField month2;
	private JTextField day2;

	public DateSelectUI() {
		setResizable(false);// ���ɷŴ�
		setBounds(100, 100, 277, 236);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		year1 = new JTextField();
		year1.setBounds(14, 38, 64, 24);
		getContentPane().add(year1);
		year1.setColumns(10);

		JLabel label = new JLabel("\u5E74");
		label.setBounds(80, 41, 15, 18);
		getContentPane().add(label);

		month1 = new JTextField();
		month1.setBounds(100, 38, 48, 24);
		getContentPane().add(month1);
		month1.setColumns(10);

		JLabel label_1 = new JLabel("\u6708");
		label_1.setBounds(148, 41, 15, 18);
		getContentPane().add(label_1);

		day1 = new JTextField();
		day1.setBounds(170, 38, 48, 24);
		getContentPane().add(day1);
		day1.setColumns(10);

		JLabel label_2 = new JLabel("\u65E5");
		label_2.setBounds(221, 41, 15, 18);
		getContentPane().add(label_2);

		year2 = new JTextField();
		year2.setColumns(10);
		year2.setBounds(14, 99, 64, 24);
		getContentPane().add(year2);

		JLabel label_3 = new JLabel("\u5E74");
		label_3.setBounds(80, 102, 15, 18);
		getContentPane().add(label_3);

		month2 = new JTextField();
		month2.setColumns(10);
		month2.setBounds(100, 99, 48, 24);
		getContentPane().add(month2);

		JLabel label_4 = new JLabel("\u6708");
		label_4.setBounds(148, 102, 15, 18);
		getContentPane().add(label_4);

		day2 = new JTextField();
		day2.setColumns(10);
		day2.setBounds(170, 99, 48, 24);
		getContentPane().add(day2);

		JLabel label_5 = new JLabel("\u65E5");
		label_5.setBounds(221, 102, 15, 18);
		getContentPane().add(label_5);

		JLabel label_6 = new JLabel("\u4ECE");
		label_6.setBounds(23, 13, 72, 18);
		getContentPane().add(label_6);

		JLabel label_7 = new JLabel("\u5230");
		label_7.setBounds(24, 75, 72, 18);
		getContentPane().add(label_7);

		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				// �ж��Ƿ�����
				int yearint1, yearint2, monthint1, monthint2, dayint1, dayint2;
				Date date1, date2;
				try {
					yearint1 = Integer.valueOf(year1.getText());
					yearint2 = Integer.valueOf(year2.getText());
					
					monthint1 = Integer.valueOf(month1.getText());
					monthint2 = Integer.valueOf(month2.getText());
					dayint1 = Integer.valueOf(day1.getText());
					dayint2 = Integer.valueOf(day2.getText());
					date1 = new Date(yearint1, monthint1, dayint1);
					date2 = new Date(yearint2, monthint2, dayint2);

					if (date1.before(date2)) {
						JFileChooser jfc = new JFileChooser();
						jfc.setFileFilter(new MyFileFilter(".xls", "Excel��ʽ"));
						jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
						int result = jfc.showSaveDialog(null);

						if (result == JFileChooser.APPROVE_OPTION) {
							File file = jfc.getSelectedFile(); // ����ļ��� // ��ñ�ѡ�еĹ�����
							MyFileFilter filter = (MyFileFilter) jfc.getFileFilter(); // ��ù���������չ��
							String ends = filter.getEnds();
							File newFile = null;
							if (file.getAbsolutePath().toUpperCase().endsWith(ends.toUpperCase())) { // ����ļ�����ѡ����չ�������ģ���ʹ��ԭ��
								newFile = file;
							} else { // �������ѡ������չ��
								newFile = new File(file.getAbsolutePath() + ends);
							} // ������ newFile ��ɱ����ļ��Ĳ���
							if (!newFile.exists()) {
								new OutExcel(newFile, date1, date2, button);
								button.setText("���ڴ���");
								button.setEnabled(false);
							} else {
								if (JOptionPane.showConfirmDialog(null, "�ļ��Ѵ��ڣ��Ƿ񸲸ǣ�", "��ʾ",
										JOptionPane.WARNING_MESSAGE,
										JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
									new OutExcel(newFile, date1, date2, button);
									button.setText("���ڴ���");
									button.setEnabled(false);
								}
							}

						}

					} else
						JOptionPane.showMessageDialog(null, "��������", "��ʾ", JOptionPane.WARNING_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "��������", "��ʾ", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		button.setBounds(148, 161, 109, 27);
		getContentPane().add(button);

		setVisible(true);
	}
}

class OutExcel extends Thread {
	File file;// Excel�ļ����ɺ�洢��λ�á�
	Date date1, date2;
	JButton btn;

	public OutExcel(File file, Date date1, Date date2, JButton btn) {
		this.file = file;
		this.date1 = date1;
		this.date2 = date2;
		this.btn = btn;
		this.start();
	}

	@SuppressWarnings("deprecation")
	public void run() {
		HSSFWorkbook wb = new HSSFWorkbook();
		// ����һ��sheet1
		HSSFSheet sheet = wb.createSheet("־Ը����Ϣ");
		// Ϊsheet1���ɵ�һ�У����ڷű�ͷ��Ϣ
		sheet.setColumnWidth(2, 4000);// �����п�
		sheet.setColumnWidth(4, 6000);// �����п�
		
		CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 6);
		sheet.addMergedRegion(cra);
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM��dd��");
		System.out.println(date1);
		System.out.println(date2);
		cell.setCellValue(date1.getYear()+"��"+dateFormat.format(date1)+"--"+date2.getYear()+"��"
		+dateFormat.format(date2)+" | ־Ը�ͳ�Ʊ�");
		
		row = sheet.createRow(1);
		// �ڶ��еĵ�һ����Ԫ���ֵΪ ����š�
		cell = row.createCell(0);
		cell.setCellValue("���");

		cell = row.createCell(1);
		cell.setCellValue("����");

		cell = row.createCell(2);
		cell.setCellValue("ѧ��");

		cell = row.createCell(3);
		cell.setCellValue("�Ա�");

		cell = row.createCell(4);
		cell.setCellValue("�μӻ");

		cell = row.createCell(5);
		cell.setCellValue("־Ըʱ��");

		cell = row.createCell(6);
		cell.setCellValue("�μ�ʱ��");

		Volenteer vol = null;
		Date date;
		int i = 1;
		// ���List�е����ݣ��������ݷŵ�Excel��
		for (Iterator<Volenteer> it = Data.group.vol.iterator(); it.hasNext();) {
			vol = it.next();
			Activity acts = null;
			// �ж��Ƿ�����ʱ�䷶Χ�ڵ�
			date = new Date();
			for (Iterator<Activity> act = vol.activities.iterator(); act.hasNext();) {
				acts = act.next();
				date.setYear(acts.getYear());
				date.setMonth(acts.getMonth());
				date.setDate(acts.getDay());
				//System.out.println(date);
				if (date.before(date2) && date.after(date1)) {
					// ����ÿ����һ�У�����������һ��
					row = sheet.createRow(++i);
					// ��һ����Ԫ�񣬷��������i�����Ӷ�����
					cell = row.createCell(0);
					cell.setCellValue(i-1);
					// �ڶ�����Ԫ���name
					cell = row.createCell(1);
					cell.setCellValue(vol.name);
					// ��������Ԫ���id
					cell = row.createCell(2);
					cell.setCellValue(vol.IDnum);
					// ���ĸ���Ԫ���sex
					cell = row.createCell(3);
					cell.setCellValue(vol.sex);
					// �������Ԫ���act
					cell = row.createCell(4);
					cell.setCellValue(acts.name);
					// ��������Ԫ���time
					cell = row.createCell(5);
					cell.setCellValue(acts.hour);
					// ���߸���Ԫ��Żʱ��
					cell = row.createCell(6);
					cell.setCellValue(acts.getYear() + "-" + acts.getMonth() + "-" + acts.getDay());
				}

			}
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				wb.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}

			byte[] content = os.toByteArray();

			OutputStream fos = null;

			try {
				fos = new FileOutputStream(file);

				fos.write(content);
				os.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		btn.setText("ȷ��");
		btn.setEnabled(true);
	}
}

class MyFileFilter extends FileFilter {
	String ends; // �ļ���׺
	String description; // �ļ���������

	public MyFileFilter(String ends, String description) { // ���캯��
		this.ends = ends; // �����ļ���׺
		this.description = description; // �����ļ���������
	}

	@Override // ֻ��ʾ������չ�����ļ���Ŀ¼ȫ����ʾ
	public boolean accept(File file) {
		if (file.isDirectory())
			return true;
		String fileName = file.getName();
		if (fileName.toUpperCase().endsWith(this.ends.toUpperCase()))
			return true;
		return false;
	}

	@Override // ���������չ��������������
	public String getDescription() {
		return this.description;
	}

	// ���������չ������������չ��
	public String getEnds() {
		return this.ends;
	}
}
