package QuanLiGiaoDien;

import java.sql.Connection;

import javax.swing.JFrame;

public class GiaoDienDichVu extends JFrame{
	Connection conn;
	public GiaoDienDichVu() {
		JFrame jfr=new JFrame("Quản lý dịch vụ");
		jfr.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jfr.setSize(500,300);
	}
}
