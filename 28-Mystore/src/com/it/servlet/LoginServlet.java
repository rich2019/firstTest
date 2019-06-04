package com.it.servlet;
/**
 * 登陆界面
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.it.domain.User;
import com.it.utils.JDBCUtilsConnection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置中文
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//取出用户的输入的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//从数据库中取值进行对比
		Connection con = JDBCUtilsConnection.getConnection();
		QueryRunner qr = new QueryRunner();
		User user = null;
		String sql = "SELECT * FROM muser WHERE username=? AND PASSWORD=?";
		try {
			user = qr.query(con,sql, new BeanHandler<User>(User.class),username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(user);
		//如果user为空则登陆失败,不为空则登陆成功
		if(user == null) {
			response.getWriter().write("<h1>用户名或密码错误</h1>");
			response.setHeader("refresh", "3;url=/28-Mystore/login.html");
		}else {
			//response.getWriter().write("登陆成功");
			//response.setHeader("refresh", "0;url=/28-Mystore/index.html");
			response.sendRedirect("/28-Mystore/index.html");
		}
		
	}

}
