package QuanLiGiaoDien;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Connect.Connect;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class GiaoDienNhanVien extends JFrame {
	private DefaultTableModel dm;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	@SuppressWarnings("unused")
	public GiaoDienNhanVien() {
		JFrame jfr = new JFrame("Quản lý nhân viên");
		jfr.setLayout(new GridLayout(1,2));
		jfr.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jfr.setSize(5000,1100);
		
		dm = new DefaultTableModel();
		dm.addColumn("Mã nhân viên");
		dm.addColumn("Tên nhân viên");
		dm.addColumn("Giới tính");
		dm.addColumn("Ngày sinh");
		dm.addColumn("Địa chỉ");
		
		JTable table = new JTable(dm);
		display();
		
		JScrollPane sc = new JScrollPane(table);
		
		JPanel jpnMain = new JPanel();
		jpnMain.setLayout(new BorderLayout());
		
		JPanel jpnLeft = new JPanel();
		jpnLeft.setBackground(Color.pink);
		jpnLeft.setLayout(new GridLayout(5,1));
		
		JLabel jlbID = new JLabel("Mã nhân viên");
		JLabel jlbName = new JLabel("Tên nhân viên");
		JLabel jlbSex = new JLabel("Giới tính");
		JLabel jlbNgaySinh = new JLabel("Ngày sinh");
		JLabel jlbDiaChi = new JLabel("Địa chỉ");
		
		jpnLeft.add(jlbID);
		jpnLeft.add(jlbName);
		jpnLeft.add(jlbSex);
		jpnLeft.add(jlbNgaySinh);
		jpnLeft.add(jlbDiaChi);
		
		JPanel jpnCenter = new JPanel();
		jpnCenter.setBackground(Color.pink);
		jpnCenter.setLayout(new GridLayout(5,1));
		
		JTextField jtfID = new JTextField(30);
		JTextField jtfName = new JTextField(30);
		JTextField jtfGioiTinh = new JTextField(30);
		JTextField jtfNgaySinh = new JTextField(30);
		JTextField jtfDiaChi = new JTextField(30);
		
		jpnCenter.add(jtfID);
		jpnCenter.add(jtfName);
		jpnCenter.add(jtfGioiTinh);
		jpnCenter.add(jtfNgaySinh);
		jpnCenter.add(jtfDiaChi);
		
		JPanel jpnSouth = new JPanel();
		jpnSouth.setBackground(Color.pink);
		jpnSouth.setLayout(new FlowLayout());
		
		JButton jbtAdd = new JButton("Add", new ImageIcon("add.png"));
		jbtAdd.setBackground(Color.white);
		JButton jbtEdit = new JButton("Edit", new ImageIcon("edit.png"));
		jbtEdit.setBackground(Color.white);
		JButton jbtDelete = new JButton("Delete", new ImageIcon("delete.png"));
		jbtDelete.setBackground(Color.white);
		JButton jbtSearch = new JButton("Search", new ImageIcon("search.png"));
		jbtSearch.setBackground(Color.white);
		JButton jbtBack = new JButton("Back", new ImageIcon("back2.png"));
		jbtBack.setBackground(Color.white);
		
		jpnSouth.add(jbtAdd);
		jpnSouth.add(jbtEdit);
		jpnSouth.add(jbtDelete);
		jpnSouth.add(jbtSearch);
		jpnSouth.add(jbtBack);
		
		table.addMouseListener((MouseListener) new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				jtfID.setText((String)table.getValueAt(row ,0));
				jtfName.setText((String)table.getValueAt(row ,1));
				jtfGioiTinh.setText((String)table.getValueAt(row ,2));
				jtfNgaySinh.setText((String)table.getValueAt(row ,3));
				jtfDiaChi.setText((String)table.getValueAt(row ,4));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		jbtAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				if (jtfID.getText() != null) {
//					conn = null;
//					stmt = null;
//					try {
//						String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
//						conn = DriverManager.getConnection(dbURL);
//						stmt = conn.createStatement();
//						String sql= "select MaNV from NHANVIEN";
//					rs = stmt.executeQuery(sql);
//					while(rs.next()) {
//						String ID = rs.getString("MaNV").trim();
//			        if (ID.equals(jtfID.getText())) {
//			        	JOptionPane.showMessageDialog(null, "Mã nhân viên đã tồn tại, vui lòng nhập lại!");
//			        }}
//				} catch(Exception e1) {
//					e1.printStackTrace();
//				}
//				} else {
				if (jtfID.getText().equalsIgnoreCase("") || jtfName.getText().equalsIgnoreCase("") || jtfGioiTinh.getText().equalsIgnoreCase("") ||jtfNgaySinh.getText().equalsIgnoreCase("") || jtfDiaChi.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Bạn chưa nhập đủ dữ liệu, vui lòng nhập lại!");
					jtfID.setText(null);
					jtfName.setText(null);
					jtfGioiTinh.setText(null);
					jtfNgaySinh.setText(null);
					jtfDiaChi.setText(null);
				} else {
				conn = null;
				stmt = null;
				try {
					String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
					conn = DriverManager.getConnection(dbURL);
					stmt = conn.createStatement();
					String sql= "insert into NHANVIEN(MaNV, TenNV, GioiTinh, NgaySinh, DiaChi) values ('"+jtfID.getText() +"','"+jtfName.getText() +"','"+jtfGioiTinh.getText() +"','"+jtfNgaySinh.getText()+"','"+jtfDiaChi.getText()+"')";
				stmt.executeUpdate(sql);
				dm.addRow(new String[] {jtfID.getText(),jtfName.getText(),jtfGioiTinh.getText(),jtfNgaySinh.getText(),jtfDiaChi.getText()});
				jtfID.setText(null);
				jtfName.setText(null);
				jtfGioiTinh.setText(null);
				jtfNgaySinh.setText(null);
				jtfDiaChi.setText(null);
				JOptionPane.showMessageDialog(null, "Thêm thành công");
				
			} catch(Exception e1) {
				e1.printStackTrace();
			}
			}
			}
			
		});
		
		jbtBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GiaoDienChinh();
				jfr.dispose();
			}
			
		});
		
		jbtDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				conn = null;
				stmt = null;
				try {
					String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
					conn = DriverManager.getConnection(dbURL);
					String sql="delete from NHANVIEN where MaNV ='"+jtfID.getText()+"'";
					stmt = conn.createStatement();
					int ch = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
					
					if (ch == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "Xóa thành công");
						int removeIndex = table.getSelectedRow();
					
					jtfID.setText(null);
					jtfName.setText(null);
					jtfGioiTinh.setText(null);
					jtfNgaySinh.setText(null);
					jtfDiaChi.setText(null);
					
					dm.removeRow(removeIndex);
					
					stmt.executeUpdate(sql);
					} else { JOptionPane.showMessageDialog(null, "Xóa thất bại");
					}
				} catch (Exception e1) {
					e1.printStackTrace();				}
				
			}
			
		});
		
		jbtEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				conn = null;
				stmt = null;
				
				try {
					String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
					conn = DriverManager.getConnection(dbURL);
					String sql="update NHANVIEN set MaNV ='"+jtfID.getText()+"', TenNV='"+jtfName.getText()+"', GioiTinh='"+jtfGioiTinh.getText()+"', NgaySinh='"+jtfNgaySinh.getText()+"', DiaCHi='"+jtfDiaChi.getText()+"' where MaNV='"+jtfID.getText()+"'";
					stmt = conn.createStatement();
					stmt.executeUpdate(sql);
					int ch = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn sửa?", "Thông báo", JOptionPane.YES_NO_OPTION);
					
					if (ch == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, "Sửa thành công");
					dm.setRowCount(0);
					display();
					jtfID.setText(null);
					jtfName.setText(null);
					jtfGioiTinh.setText(null);
					jtfNgaySinh.setText(null);
					jtfDiaChi.setText(null);
					
					} else if (ch == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "Sửa thất bại");
					}
				} catch (Exception e1) {
					e1.printStackTrace();				}
				
			}
			
		});
		
		jbtSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GiaoDienTimKiem();

				jfr.dispose();
			}
			
		});
		
		
		jpnMain.add(jpnLeft, BorderLayout.WEST);
		jpnMain.add(jpnCenter, BorderLayout.CENTER);
		jpnMain.add(jpnSouth, BorderLayout.SOUTH);
		
		jfr.add(jpnMain);
		jfr.add(sc);
		
		jfr.pack();
		jfr.setVisible(true);
		jfr.setLocationRelativeTo(null);
	}
	
	public void display() {
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
	

}
