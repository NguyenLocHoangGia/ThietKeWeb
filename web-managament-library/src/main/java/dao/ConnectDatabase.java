package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class ConnectDatabase {
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/libman";
			String username = "root";
			String password = "123456789";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			c = DriverManager.getConnection(url, username, password);
			
			DriverManager.registerDriver(new Driver());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static void closeConnection(Connection c) {
		try {
			if(c != null) {
				c.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void printInfo(Connection c) {
		try {
			if (c != null) {
				DatabaseMetaData m = c.getMetaData();
				System.out.println(m.getDatabaseProductName());
				System.out.println(m.getDatabaseProductVersion());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
