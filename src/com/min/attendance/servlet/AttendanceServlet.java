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
		System.out.println("Attendance连接成功action---" + action);

		if (action.equals("login")) {// 登录
			result = login(request);
		} else if (action.equals("register")) {// 注册
			result = register(request);
		} else if (action.equals("getcurriculum")) { // 获取课表
			result = getCurriculum(request);
		} else if (action.equals("getAttendance")) { // 获取考勤记录
			result = getAttendance(request);
		} else if (action.equals("changeAttendance")) { // 更改考勤
			result = changeAttendance(request);
		}

		System.out.println("输出result ----" + result);
		out.println(result);
		out.flush();
		out.close();
	}

	// 登录,根据用户的身份、账号、密码与数据库数据进行匹配
	public String login(HttpServletRequest request) {
		String result = "";
		try {
			String identity = request.getParameter("identity");
			String username = request.getParameter("username");
			String psssword = request.getParameter("psd");
			String sql = "";
			System.out.println("identity==" + identity);
			if (identity.equals("0")) { // 学生
				sql = "select students.*,classes.class_name from students INNER JOIN classes on classes.class_id = students.student_classid where students.student_id="
						+ username + " and students.student_password='" + psssword + "'";
			} else { // 教师
				sql = "select * from teachers where teacher_id=" + username + " and teacher_password='" + psssword
						+ "'";
			}
			ResultSet rs = connetSQL.queryData(sql);

			if (rs.next()) {
				List list = ListUtil.RsToList(rs);
				result = ListUtil.listToJson(list);
			} else {
				if (identity.equals("0")) { // 学生
					sql = "select * from students where student_id=" + username;
				} else { // 教师
					sql = "select * from teachers where teacher_id=" + username;
				}
				ResultSet rs2 = connetSQL.queryData(sql);
				if (rs2.next()) { // 用户存在，密码错误的情况
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

	// 注册
	public String register(HttpServletRequest request) {
		String result = "";
		String userName = request.getParameter("username");
		String userPassword = request.getParameter("psd");
		String phone = request.getParameter("telephone");
		String sql = "select * from students where student_id=" + userName;
		ResultSet rs = connetSQL.queryData(sql);
		if (rs == null) {// 没有被注册

		} else {
			result = "2"; // 说明该用户名已经被占用
		}
		return result;
	}

	// 获得课程
	public String getCurriculum(HttpServletRequest request) {
		String result = "";
		return result;
	}

	// 获得考勤情况
	public String getAttendance(HttpServletRequest request) {
		String result = "";
		return result;
	}

	// 修改某位同学的考勤情况
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
