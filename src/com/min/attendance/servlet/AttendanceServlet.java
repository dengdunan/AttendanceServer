package com.min.attendance.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.attendance.util.ConnectSQL;
import com.min.attendance.util.ListUtil;

public class AttendanceServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectSQL connetSQL = new ConnectSQL();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("Action");
		String result = "";
		System.out.println("Attendance���ӳɹ�action---" + action);

		if (action.equals("login")) {// ��¼
			result = login(request);
		} else if (action.equals("register")) {// ע��
			result = register(request);
		} else if (action.equals("getcurriculum")) { // ��ȡ�α�
			result = getCurriculum(request);
		} else if (action.equals("getAttendance")) { // ��ȡ���ڼ�¼
			result = getAttendance(request);
		} else if (action.equals("changeAttendance")) { // ���Ŀ���
			result = changeAttendance(request);
		}

		System.out.println("���result ----" + result);
		out.println(result);
		out.flush();
		out.close();
	}

	// ��¼,�����û�����ݡ��˺š����������ݿ����ݽ���ƥ��
	public String login(HttpServletRequest request) {
		String result = "";
		try {
			String identity = request.getParameter("identity");
			String username = request.getParameter("username");
			String psssword = request.getParameter("psd");
			String sql = "";
			System.out.println("identity==" + identity);
			if (identity.equals("0")) { // ѧ��
				sql = "select students.*,classes.class_name from students INNER JOIN classes on classes.class_id = students.student_classid where students.student_id="
						+ username + " and students.student_password='" + psssword + "'";
			} else { // ��ʦ
				sql = "select * from teachers where teacher_id=" + username + " and teacher_password='" + psssword
						+ "'";
			}
			ResultSet rs = connetSQL.queryData(sql);

			if (rs.next()) {
				List list = ListUtil.RsToList(rs);
				result = ListUtil.listToJson(list);
			} else {
				if (identity.equals("0")) { // ѧ��
					sql = "select * from students where student_id=" + username;
				} else { // ��ʦ
					sql = "select * from teachers where teacher_id=" + username;
				}
				ResultSet rs2 = connetSQL.queryData(sql);
				if (rs2.next()) { // �û����ڣ������������
					result = "2";
				} else {
					result = "3";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connetSQL.closeConnection();
		}
		System.out.println(result == null);
		return result;
	}

	// ע��
	public String register(HttpServletRequest request) {
		String result = "";
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("psd");
		String phone = request.getParameter("telephone");
		String sql = "select * from students where student_id=" + userName;
		ResultSet rs = connetSQL.queryData(sql);
		if (rs == null) {// û�б�ע��

		} else {
			result = "2"; // ˵�����û����Ѿ���ռ��
		}
		return result;
	}

	// ��ÿγ�
	public String getCurriculum(HttpServletRequest request) {
		String result = "";
		return result;
	}

	// ��ÿ������
	public String getAttendance(HttpServletRequest request) {
		String result = "";
		return result;
	}

	// �޸�ĳλͬѧ�Ŀ������
	public String changeAttendance(HttpServletRequest request) {
		String result = "";
		String attendanceID = request.getParameter("attendanceId");
		String sql = "update attendance set attendance_id ='" + attendanceID + "'";
		return result;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
