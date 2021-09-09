package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// 对数据库连接操作的对象
public class DbUtil {	
	/**
	 * 封装数据库连接基本方法
	 * */
	// 返回连接对象
	public static Connection getConnection() {
		// 8.0版本以下的数据库连接
		String DB_Name = "studentsdb";
	    String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	     String DB_URL = "jdbc:mysql://localhost:3306/"+DB_Name+"?"
	    		+ "useSSL=true&character=utf-8";
	    // 连接mysql的ip地址，用户，密码
	 // 数据库的用户名与密码，需要根据自己的设置
	    String user = "root";
	    String passWord = "root";
	    
	    Connection conn = null;
	    try {
			//  加载驱动
			Class.forName(JDBC_DRIVER);
			 // 连接对象
			conn = DriverManager.getConnection(DB_URL, user, passWord);
	    }catch (Exception e) {}
		return conn;
	    }
	public static void close(Connection conn) {
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(PreparedStatement preStat) {
		if (preStat != null) {
			try {
				preStat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
