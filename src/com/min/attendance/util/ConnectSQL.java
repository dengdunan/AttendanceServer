package com.min.attendance.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 服务器端连接数据库处理类
 */
public class ConnectSQL {
	private String driver = "com.mysql.jdbc.Driver";
	private Connection con = null;
	private String url = "jdbc:mysql://localhost:3306/attendance_db";
	private String users = "root";
	private String userpassword = "20134675";
	private ResultSet rResult = null;
	private Statement stmt = null;

	public ConnectSQL() {
		/* 建立连接 */
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, users, userpassword);
		} catch (ClassNotFoundException e) {
			// 声明驱动程序时报错
			e.printStackTrace();
		} catch (SQLException e) {
			// 数据库操作出错
			e.printStackTrace();
		}
	}

	/*
	 * 根据SQL语句对数据库进行操作，并返回相应的结果集
	 */
	public ResultSet queryData(String sSQL) {

		try {
			stmt = con.createStatement();
			rResult = stmt.executeQuery(sSQL);
		} catch (Exception e) {
			// 数据库操作出错
			e.printStackTrace();
		}
		return rResult;
	}

	public void closeConnection() {
		// 关闭连接
		try {
			if (rResult != null)
				rResult.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
