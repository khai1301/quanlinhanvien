package QuanLiGiaoDien;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;

@SuppressWarnings("serial")
public class GiaoDienDangNhap extends JFrame {
	Connection conn;
	Statement stmt;
	ResultSet rs;
	public GiaoDienDangNhap() {
	JFrame jfr = new JFrame("Login");
	jfr.setDefaultCloseOperation(EXIT_ON_CLOSE);
	jfr.setSize(150,50);
	
	JPanel jpnNorth = new JPanel();
	jpnNorth.setLayout(new GridLayout(1, 1));
	jpnNorth.setBackground(Color.pink);
	
	JLabel jblLogin = new JLabel("Login");
	jblLogin.setHorizontalAlignment(JLabel.CENTER);
	jpnNorth.add(jblLogin);
	
	JPanel jpnLeft = new JPanel();
	jpnLeft.setLayout(new GridLayout(3, 1));
	jpnLeft.setBackground(Color.pink);
	
	JLabel jlbUser = new JLabel("User");
	JLabel jlbPass = new JLabel("Password");
	
	jpnLeft.add(jlbUser);
	jpnLeft.add(jlbPass);
	
	JPanel jpnCenter = new JPanel();
	jpnCenter.setLayout(new GridLayout(3,1));
	jpnCenter.setBackground(Color.pink);
	
	JTextField jtfUser = new JTextField(30);
	JPasswordField jpfPass = new JPasswordField(30);
	
	JLabel txtCheck = new JLabel("See password");
	JCheckBox checkBox = new JCheckBox();
	  checkBox.addActionListener(new ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	    if(checkBox.isSelected()){
	     txtCheck.setText("Hide password");
	     jpfPass.setEchoChar((char)0);
	    }else{
	     txtCheck.setText("See password");
	     jpfPass.setEchoChar('*');
	    }
	   }
	  });
	  
	  JPanel jpnct = new JPanel();
	  jpnct.setBackground(Color.pink);
	  jpnct.setLayout(new FlowLayout());
	  jpnct.add(checkBox);
	  jpnct.add(txtCheck);
	  
	jpnCenter.add(jtfUser);
	jpnCenter.add(jpfPass);
	jpnCenter.add(jpnct);
	
	JPanel jpnSouth = new JPanel();
	jpnSouth.setLayout(new FlowLayout());
	jpnSouth.setBackground(Color.pink);
	
	JButton jbtLogin = new JButton("Login", new ImageIcon("login.png"));
	jbtLogin.setBackground(Color.white);
	JButton jbtExit = new JButton("Exit", new ImageIcon("exit.png"));
	jbtExit.setBackground(Color.white);
	JButton jbtdmk = new JButton("Change", new ImageIcon("doimk.png"));
	jbtdmk.setBackground(Color.white);
	
	jpnSouth.add(jbtLogin);
	jpnSouth.add(jbtExit);
	jpnSouth.add(jbtdmk);
	
	jfr.add(jpnNorth, BorderLayout.NORTH);
	jfr.add(jpnLeft, BorderLayout.WEST);
	jfr.add(jpnCenter, BorderLayout.CENTER);
	jfr.add(jpnSouth, BorderLayout.SOUTH);
	
	jbtExit.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}		
	});
	jbtLogin.addActionListener(new ActionListener() {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			conn = null;
			stmt = null;
			try {
				String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
				conn = DriverManager.getConnection(dbURL);
				stmt = conn.createStatement();
				String sql = "Select * from TAIKHOAN";
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					 String user = rs.getString("TaiKhoan").trim();
					 String pass = rs.getString("MatKhau").trim();
					if (jtfUser.getText().equals(user) && jpfPass.getText().equals(pass) ){
						JOptionPane.showMessageDialog(null, "Login Sucessful");
						 new GiaoDienChinh();
						 jfr.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Login Fail");
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		
	});
	jbtdmk.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jfr.dispose();
			doimk();
		}
		
	});

	jfr.pack();
	jfr.setVisible(true);
	jfr.setLocationRelativeTo(null);
	
	}
	public void doimk() {
		Connection conn;
		Statement stmt;
		ResultSet rs;
		JFrame jfr = new JFrame("Login");
		jfr.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jfr.setSize(150,50);
		
		JPanel jpnNorth = new JPanel();
		jpnNorth.setLayout(new GridLayout(1, 1));
		jpnNorth.setBackground(Color.pink);
		
		JLabel jblLogin = new JLabel("Change Password");
		jblLogin.setHorizontalAlignment(JLabel.CENTER);
		jpnNorth.add(jblLogin);
		
		JPanel jpnLeft = new JPanel();
		jpnLeft.setLayout(new GridLayout(3, 1));
		jpnLeft.setBackground(Color.pink);
		
		JLabel jlbUser = new JLabel("User");
		JLabel jlbPass = new JLabel("New Password");
		
		jpnLeft.add(jlbUser);
		jpnLeft.add(jlbPass);
		
		JPanel jpnCenter = new JPanel();
		jpnCenter.setLayout(new GridLayout(3,1));
		jpnCenter.setBackground(Color.pink);
		
		JTextField jtfUser = new JTextField(30);
		JPasswordField jpfPass = new JPasswordField(30);
		
		JLabel txtCheck = new JLabel("See password");
		JCheckBox checkBox = new JCheckBox();
		  checkBox.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
		    if(checkBox.isSelected()){
		     txtCheck.setText("Hide password");
		     jpfPass.setEchoChar((char)0);
		    }else{
		     txtCheck.setText("See password");
		     jpfPass.setEchoChar('*');
		    }
		   }
		  });
		  
		  JPanel jpnct = new JPanel();
		  jpnct.setBackground(Color.pink);
		  jpnct.setLayout(new FlowLayout());
		  jpnct.add(checkBox);
		  jpnct.add(txtCheck);
		  
		jpnCenter.add(jtfUser);
		jpnCenter.add(jpfPass);
		jpnCenter.add(jpnct);
		
		JPanel jpnSouth = new JPanel();
		jpnSouth.setLayout(new FlowLayout());
		jpnSouth.setBackground(Color.pink);
		
		JButton jbtChange = new JButton("Change", new ImageIcon("change.png"));
		jbtChange.setBackground(Color.white);
		JButton jbtExit = new JButton("Exit", new ImageIcon("exit.png"));
		jbtExit.setBackground(Color.white);
		
		jpnSouth.add(jbtChange);
		jpnSouth.add(jbtExit);
		
		jfr.add(jpnNorth, BorderLayout.NORTH);
		jfr.add(jpnLeft, BorderLayout.WEST);
		jfr.add(jpnCenter, BorderLayout.CENTER);
		jfr.add(jpnSouth, BorderLayout.SOUTH);
		
		jbtExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jfr.dispose();
				new GiaoDienDangNhap();
			}		
		});
		jbtChange.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				try {
					String dbURL = "jdbc:sqlserver://DESKTOP-Q75IJIO\\SQLEXPRESS:1433;databaseName=Dichvu1080;user=sa;password=sa";
					conn = DriverManager.getConnection(dbURL);
					stmt = conn.createStatement();
					String sql= "select TaiKhoan, MatKhau from TAIKHOAN";
				rs = stmt.executeQuery(sql);
		        while(rs.next()) {
		        	String user = rs.getString("TaiKhoan").trim();
		        	String pass = rs.getString("MatKhau").trim();
		        	if (pass.equals(jpfPass.getText())) {
		        		JOptionPane.showMessageDialog(null,"Đây là mật khẩu cũ, vui lòng nhập mật khẩu mới!");
		        	} else 
		        	if (user.equals(jtfUser.getText())) {
		        		{
		        		String sql1 = "Update TAIKHOAN set MatKhau ='"+jpfPass.getText()+"'";
						stmt.executeUpdate(sql1);
						JOptionPane.showMessageDialog(null, "Change Password Successful");
		        		}
		        	} else {
		        		JOptionPane.showMessageDialog(null, "Change Password Fail");
		        } 
		        	
		        }
			} catch(Exception e1) {
				e1.printStackTrace();
			} finally {
						try {
							rs.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
			}
			}
			
		});
		jfr.pack();
		jfr.setVisible(true);
		jfr.setLocationRelativeTo(null);
	}
	
}
