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
	private String driver = "com.mysql.jdbc.Driver";
	private Connection con = null;
	private String url = "jdbc:mysql://localhost:3306/attendance_db";
	private String users = "root";
	private String userpassword = "20134675";
	private ResultSet rResult = null;
	private Statement stmt = null;

	public ConnectSQL() {
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
	}

	/*
	 * ����SQL�������ݿ���в�������������Ӧ�Ľ����
	 */
	public ResultSet queryData(String sSQL) {

		try {
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
