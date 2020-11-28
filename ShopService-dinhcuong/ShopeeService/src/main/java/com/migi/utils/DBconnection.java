package com.migi.utils;
import java.sql.*;
public class DBconnection {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); //load driver useUnicode=true&amp;
			String url="jdbc:mysql://localhost:3306/qlbanhang?useUnicode=true&characterEncoding=utf8";
			String username ="root";
			String password="13031997";
			conn=DriverManager.getConnection(url,username,password);
			//conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","123456");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
