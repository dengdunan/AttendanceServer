package com.min.attendance.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * ���������������ݿ⴦����
 */
public class ConnectSQL {
	String driver = "com.mysql.jdbc.Driver";
	Connection con = null;
	String url = "jdbc:mysql://localhost:3306/attendance_db";
	String users = "root";
	String userpassword = "20134675";
	ResultSet rResult = null;
	Statement stmt = null;

	public Connection connetSQL() {
		/* �������� */
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, users, userpassword);
		} catch (ClassNotFoundException e) {
			// ������������ʱ����
			e.printStackTrace();
		} catch (SQLException e) {
			// ���ݿ��������
			e.printStackTrace();
		}
		return con;
	}

	/*
	 * ����SQL�������ݿ���в�������������Ӧ�Ľ����
	 */
	public ResultSet queryData(String sSQL) {

		try {
			/* �������� */
			Class.forName(driver);
			con = DriverManager.getConnection(url, users, userpassword);
			stmt = con.createStatement();
			rResult = stmt.executeQuery(sSQL);
		} catch (Exception e) {
			// ���ݿ��������
			e.printStackTrace();
		}
		return rResult;
	}

	public void closeConnection() {
		// �ر�����
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
