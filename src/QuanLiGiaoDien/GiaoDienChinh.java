package QuanLiGiaoDien;

import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;



@SuppressWarnings("serial")
public class GiaoDienChinh extends JFrame {
	Connection conn;
	
	public GiaoDienChinh() {
		
	JFrame jfr = new JFrame("Trang Chủ");
	jfr.setSize(10000,250);
	jfr.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	JPanel jpnNorth = new JPanel();
	jpnNorth.setLayout(new GridLayout(1,1));
	jpnNorth.setBackground(Color.pink);
	
	JLabel jlbDV = new JLabel("Vui lòng chọn chức năng");
	jlbDV.setHorizontalAlignment(JLabel.CENTER);

	jpnNorth.add(jlbDV);
	
	JPanel jpnCenter = new JPanel();
	jpnCenter.setLayout(new GridLayout(1,2));
	jpnCenter.setBackground(Color.pink);
	
	JButton jbtDV1 = new JButton("Quản lý nhân viên", new ImageIcon("qlnvien.png"));
	jbtDV1.setBackground(Color.pink);
	jpnCenter.add(jbtDV1);
	
	JButton jbtBack = new JButton("Back", new ImageIcon("back.png"));
	jbtBack.setBackground(Color.pink);
	jbtBack.setHorizontalAlignment(JLabel.CENTER);

	jpnCenter.add(jbtBack);
	
	jbtBack.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new GiaoDienDangNhap();
			jfr.dispose();
		}
		
	});
	
	jbtDV1.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new GiaoDienNhanVien();
			jfr.dispose();
			
		}
		
	});
	

	jfr.add(jpnCenter, BorderLayout.CENTER);
	jfr.add(jpnNorth, BorderLayout.NORTH);
	
	jfr.pack();
	jfr.setVisible(true);
	jfr.setLocationRelativeTo(null);
	}

}
