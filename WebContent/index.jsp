<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<body>
    <form action="/LoginServlet/servlet/LoginServlet"  method="post" >
        用户名：<input type="text" name="username"> <br>
        密 码：<input type="password" name="psd" maxlength=6>
        <br>
        <input  type="submit"  value="提交">
        <input  type="reset"  value="重填">
    </form>
  </body>
