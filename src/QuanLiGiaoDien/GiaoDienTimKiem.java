package QuanLiGiaoDien;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;

public class GiaoDienTimKiem extends JFrame {
	private DefaultTableModel dm;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	public GiaoDienTimKiem() {
		JFrame jfr = new JFrame("Tìm kiếm nhân viên");jfr.setLayout(new GridLayout(1,2));
		jfr.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel jpnMain = new JPanel();
		jpnMain.setLayout(new GridLayout(3,1));
		jpnMain.setSize(500,500);
		
		JMenuBar mb = new JMenuBar();
		JMenu tttk = new JMenu("Thông tin tìm kiếm");
		JMenuItem manv = new JMenuItem("Mã nhân viên");
		manv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_manv();
			}
			
		});
		JMenuItem diachi = new JMenuItem("Địa chỉ");
		diachi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_diachi();
			}
			
		});
		JMenuItem tennv = new JMenuItem("Tên nhân viên");
		tennv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_tennv();
			}
			
		});
		
		mb.add(tttk);
		tttk.add(manv);
		tttk.add(diachi);
		tttk.add(tennv);
		
		JLabel jlbTT = new JLabel("THÔNG TIN TÌM KIẾM");
		jlbTT.setFont(new Font("Calibri", Font.BOLD, 30));
		jlbTT.setHorizontalAlignment(JLabel.CENTER);
		
		
		JPanel jpnCenter = new JPanel();
		jpnCenter.setLayout(new GridLayout(5,2));
		
		JTextField jtfID = new JTextField(30);
		JLabel jlbID = new JLabel("Địa chỉ nhân viên");
		
		jpnCenter.add(jlbID);
		jpnCenter.add(jtfID);

		
		JPanel jpn3 = new JPanel();
		jpn3.setLayout(new FlowLayout());
		
		JButton jbtSearch = new JButton("Search", new ImageIcon("search.png"));
		jbtSearch.setBackground(Color.white);
		JButton jbtBack = new JButton("Back", new ImageIcon("back2.png"));
		jbtBack.setBackground(Color.white);
		JButton jbtRefresh = new JButton("Refresh", new ImageIcon("refresh.png"));
		jbtRefresh.setBackground(Color.white);
		
		jpn3.add(jbtSearch);
		jpn3.add(jbtBack);
		jpn3.add(jbtRefresh);
		
		dm = new DefaultTableModel();
		dm.addColumn("Mã nhân viên");
		dm.addColumn("Tên nhân viên");
		dm.addColumn("Giới tính");
		dm.addColumn("Ngày sinh");
		dm.addColumn("Địa chỉ");
		
		JTable table = new JTable(dm);
		
		JScrollPane sc = new JScrollPane();
		sc.setViewportView(table);
		
		jbtSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jtfID.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ dữ liêu, vui lòng nhập lại");
				} else {
					dm.setRowCount(0);
					conn = null;
					stmt = null;
					try {
						String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
						conn = DriverManager.getConnection(dbURL);
						stmt = conn.createStatement();
						String sql= "select * from NHANVIEN where DiaChi = '"+jtfID.getText()+"'";
					rs = stmt.executeQuery(sql);
					jtfID.setText(null);
					while(rs.next()) {
						dm.addRow(new String[] {rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("GioiTinh"), rs.getString("NgaySinh"), rs.getString("DiaChi")});
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				}	
			}
			
		});
		
		jbtBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GiaoDienNhanVien();
				jfr.dispose();
			}
			
		});
		
		jbtRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dm.setRowCount(0);
				display();
			}
			
		});
		
		jpnMain.add(jlbTT);
		jpnMain.add(jpnCenter); 
		jpnMain.add(jpn3);
		
		jfr.add(jpnMain);
		jfr.add(sc);
		jfr.setJMenuBar(mb);
		
		
		
		jfr.pack();
		jfr.setVisible(true);
		jfr.setLocationRelativeTo(null);
		display();
	}
	public void display() {
		conn = null;
		stmt = null;
		try {
		String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
		conn = DriverManager.getConnection(dbURL);
		stmt = conn.createStatement();
		String sql= "select * from NHANVIEN";
		rs = stmt.executeQuery(sql);
		while(rs.next()) {
    	dm.addRow(new String[] {rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("GioiTinh"), rs.getString("NgaySinh"), rs.getString("DiaChi")});
		}
		} catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	public void tk_manv() {
		JFrame jfr = new JFrame("Tìm kiếm nhân viên");jfr.setLayout(new GridLayout(1,2));
		jfr.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel jpnMain = new JPanel();
		jpnMain.setLayout(new GridLayout(3,1));
		jpnMain.setSize(500,500);
		
		JMenuBar mb = new JMenuBar();
		JMenu tttk = new JMenu("Thông tin tìm kiếm");
		JMenuItem manv = new JMenuItem("Mã nhân viên");
		manv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_manv();
			}
			
		});
		JMenuItem diachi = new JMenuItem("Địa chỉ");
		diachi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_diachi();
			}
			
		});
		JMenuItem tennv = new JMenuItem("Tên nhân viên");
		tennv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_tennv();
			}
			
		});
		
		mb.add(tttk);
		tttk.add(manv);
		tttk.add(diachi);
		tttk.add(tennv);
		
		JLabel jlbTT = new JLabel("THÔNG TIN TÌM KIẾM");
		jlbTT.setFont(new Font("Calibri", Font.BOLD, 30));
		jlbTT.setHorizontalAlignment(JLabel.CENTER);
		
		
		JPanel jpnCenter = new JPanel();
		jpnCenter.setLayout(new GridLayout(5,2));
		
		JTextField jtfID = new JTextField(30);
		JLabel jlbID = new JLabel("Mã nhân viên");
		
		jpnCenter.add(jlbID);
		jpnCenter.add(jtfID);

		
		JPanel jpn3 = new JPanel();
		jpn3.setLayout(new FlowLayout());
		
		JButton jbtSearch = new JButton("Search", new ImageIcon("search.png"));
		jbtSearch.setBackground(Color.white);
		JButton jbtBack = new JButton("Back", new ImageIcon("back2.png"));
		jbtBack.setBackground(Color.white);
		JButton jbtRefresh = new JButton("Refresh", new ImageIcon("refresh.png"));
		jbtRefresh.setBackground(Color.white);
		
		jpn3.add(jbtSearch);
		jpn3.add(jbtBack);
		jpn3.add(jbtRefresh);
		
		dm = new DefaultTableModel();
		dm.addColumn("Mã nhân viên");
		dm.addColumn("Tên nhân viên");
		dm.addColumn("Giới tính");
		dm.addColumn("Ngày sinh");
		dm.addColumn("Địa chỉ");
		
		JTable table = new JTable(dm);
		
		JScrollPane sc = new JScrollPane();
		sc.setViewportView(table);
		
		jbtSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jtfID.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ dữ liêu, vui lòng nhập lại");
				} else {
					dm.setRowCount(0);
					conn = null;
					stmt = null;
					try {
						String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
						conn = DriverManager.getConnection(dbURL);
						stmt = conn.createStatement();
						String sql= "select * from NHANVIEN where MaNV = '"+jtfID.getText()+"'";
					rs = stmt.executeQuery(sql);
					jtfID.setText(null);
					while(rs.next()) {
						dm.addRow(new String[] {rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("GioiTinh"), rs.getString("NgaySinh"), rs.getString("DiaChi")});
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				}	
			}
			
		});
		
		jbtBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GiaoDienNhanVien();
				jfr.dispose();
			}
			
		});
		
		jbtRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dm.setRowCount(0);
				display();
			}
			
		});
		
		jpnMain.add(jlbTT);
		jpnMain.add(jpnCenter); 
		jpnMain.add(jpn3);
		
		jfr.setJMenuBar(mb);
		jfr.add(jpnMain);
		jfr.add(sc);
		
		jfr.pack();
		jfr.setVisible(true);
		jfr.setLocationRelativeTo(null);
		display();
	}
	public void tk_tennv() {
		JFrame jfr = new JFrame("Tìm kiếm nhân viên");jfr.setLayout(new GridLayout(1,2));
		jfr.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel jpnMain = new JPanel();
		jpnMain.setLayout(new GridLayout(3,1));
		jpnMain.setSize(500,500);
		
		JMenuBar mb = new JMenuBar();
		JMenu tttk = new JMenu("Thông tin tìm kiếm");
		JMenuItem manv = new JMenuItem("Mã nhân viên");
		manv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_manv();
			}
			
		});
		JMenuItem diachi = new JMenuItem("Địa chỉ");
		diachi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_diachi();
			}
			
		});
		JMenuItem tennv = new JMenuItem("Tên nhân viên");
		tennv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_tennv();
			}
			
		});
		
		mb.add(tttk);
		tttk.add(manv);
		tttk.add(diachi);
		tttk.add(tennv);
		
		JLabel jlbTT = new JLabel("THÔNG TIN TÌM KIẾM");
		jlbTT.setFont(new Font("Calibri", Font.BOLD, 30));
		jlbTT.setHorizontalAlignment(JLabel.CENTER);
		
		
		JPanel jpnCenter = new JPanel();
		jpnCenter.setLayout(new GridLayout(5,2));
		
		JTextField jtfID = new JTextField(30);
		JLabel jlbID = new JLabel("Tên nhân viên");
		
		jpnCenter.add(jlbID);
		jpnCenter.add(jtfID);

		
		JPanel jpn3 = new JPanel();
		jpn3.setLayout(new FlowLayout());
		
		JButton jbtSearch = new JButton("Search", new ImageIcon("search.png"));
		jbtSearch.setBackground(Color.white);
		JButton jbtBack = new JButton("Back", new ImageIcon("back2.png"));
		jbtBack.setBackground(Color.white);
		JButton jbtRefresh = new JButton("Refresh", new ImageIcon("refresh.png"));
		jbtRefresh.setBackground(Color.white);
		
		jpn3.add(jbtSearch);
		jpn3.add(jbtBack);
		jpn3.add(jbtRefresh);
		
		dm = new DefaultTableModel();
		dm.addColumn("Mã nhân viên");
		dm.addColumn("Tên nhân viên");
		dm.addColumn("Giới tính");
		dm.addColumn("Ngày sinh");
		dm.addColumn("Địa chỉ");
		
		JTable table = new JTable(dm);
		
		JScrollPane sc = new JScrollPane();
		sc.setViewportView(table);
		
		jbtSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jtfID.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ dữ liêu, vui lòng nhập lại");
				} else {
					dm.setRowCount(0);
					conn = null;
					stmt = null;
					try {
						String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
						conn = DriverManager.getConnection(dbURL);
						stmt = conn.createStatement();
						String sql= "select * from NHANVIEN where TenNV = '"+jtfID.getText()+"'";
					rs = stmt.executeQuery(sql);
					jtfID.setText(null);
					while(rs.next()) {
						dm.addRow(new String[] {rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("GioiTinh"), rs.getString("NgaySinh"), rs.getString("DiaChi")});
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				}	
			}
			
		});
		
		jbtBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GiaoDienNhanVien();
				jfr.dispose();
			}
			
		});
		
		jbtRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dm.setRowCount(0);
				display();
			}
			
		});
		
		jpnMain.add(jlbTT);
		jpnMain.add(jpnCenter); 
		jpnMain.add(jpn3);
		
		jfr.setJMenuBar(mb);
		jfr.add(jpnMain);
		jfr.add(sc);
		
		jfr.pack();
		jfr.setVisible(true);
		jfr.setLocationRelativeTo(null);
		display();
	}
	
	public void tk_diachi() {
		JFrame jfr = new JFrame("Tìm kiếm nhân viên");jfr.setLayout(new GridLayout(1,2));
		jfr.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		JPanel jpnMain = new JPanel();
		jpnMain.setLayout(new GridLayout(3,1));
		jpnMain.setSize(500,500);
		
		JMenuBar mb = new JMenuBar();
		JMenu tttk = new JMenu("Thông tin tìm kiếm");
		JMenuItem manv = new JMenuItem("Mã nhân viên");
		manv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_manv();
			}
			
		});
		JMenuItem diachi = new JMenuItem("Địa chỉ");
		diachi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_diachi();
			}
			
		});
		JMenuItem tennv = new JMenuItem("Tên nhân viên");
		tennv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				tk_tennv();
			}
			
		});
		
		mb.add(tttk);
		tttk.add(manv);
		tttk.add(diachi);
		tttk.add(tennv);
		
		JLabel jlbTT = new JLabel("THÔNG TIN TÌM KIẾM");
		jlbTT.setFont(new Font("Calibri", Font.BOLD, 30));
		jlbTT.setHorizontalAlignment(JLabel.CENTER);
		
		
		JPanel jpnCenter = new JPanel();
		jpnCenter.setLayout(new GridLayout(5,2));
		
		JTextField jtfID = new JTextField(30);
		JLabel jlbID = new JLabel("Địa chỉ nhân viên");
		
		jpnCenter.add(jlbID);
		jpnCenter.add(jtfID);

		
		JPanel jpn3 = new JPanel();
		jpn3.setLayout(new FlowLayout());
		
		JButton jbtSearch = new JButton("Search", new ImageIcon("search.png"));
		jbtSearch.setBackground(Color.white);
		JButton jbtBack = new JButton("Back", new ImageIcon("back2.png"));
		jbtBack.setBackground(Color.white);
		JButton jbtRefresh = new JButton("Refresh", new ImageIcon("refresh.png"));
		jbtRefresh.setBackground(Color.white);
		
		jpn3.add(jbtSearch);
		jpn3.add(jbtBack);
		jpn3.add(jbtRefresh);
		
		dm = new DefaultTableModel();
		dm.addColumn("Mã nhân viên");
		dm.addColumn("Tên nhân viên");
		dm.addColumn("Giới tính");
		dm.addColumn("Ngày sinh");
		dm.addColumn("Địa chỉ");
		
		JTable table = new JTable(dm);
		
		JScrollPane sc = new JScrollPane();
		sc.setViewportView(table);
		
		jbtSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (jtfID.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ dữ liêu, vui lòng nhập lại");
				} else {
					dm.setRowCount(0);
					conn = null;
					stmt = null;
					try {
						String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
						conn = DriverManager.getConnection(dbURL);
						stmt = conn.createStatement();
						String sql= "select * from NHANVIEN where DiaChi = '"+jtfID.getText()+"'";
					rs = stmt.executeQuery(sql);
					jtfID.setText(null);
					while(rs.next()) {
						dm.addRow(new String[] {rs.getString("MaNV"), rs.getString("TenNV"), rs.getString("GioiTinh"), rs.getString("NgaySinh"), rs.getString("DiaChi")});
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				}	
			}
			
		});
		
		jbtBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GiaoDienNhanVien();
				jfr.dispose();
			}
			
		});
		
		jbtRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dm.setRowCount(0);
				display();
			}
			
		});
		
		jpnMain.add(jlbTT);
		jpnMain.add(jpnCenter); 
		jpnMain.add(jpn3);
		
		
		jfr.setJMenuBar(mb);
		jfr.add(jpnMain);
		jfr.add(sc);
		
		
		jfr.pack();
		jfr.setVisible(true);
		jfr.setLocationRelativeTo(null);
		display();
	}
	
}
