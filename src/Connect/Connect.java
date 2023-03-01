package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import QuanLiGiaoDien.GiaoDienDangNhap;

public class Connect {
public static void main(String [] args)  {
	Connection conn;{
	try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
		conn =  (Connection) DriverManager.getConnection(dbURL);
		if (conn != null ) {
			System.out.println("Connected");
		}
	}  catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	GiaoDienDangNhap x = new GiaoDienDangNhap();
	x.pack();
}
}
