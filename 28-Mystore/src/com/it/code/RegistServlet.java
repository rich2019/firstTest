package com.it.code;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.it.domain.User;
import com.it.utils.JDBCUtilsConnection;


@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.设置中文编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String checkCode = (String)this.getServletContext().getAttribute("checkCode");
		String code = request.getParameter("code");
		if(checkCode.equals(code)) {
			
			//2.将数据存储在domain类中
			Map<String, String[]> map = request.getParameterMap();
			User user = new User();
			user.setUid(UUID.randomUUID().toString());
			try {
				BeanUtils.populate(user, map);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			System.out.println(user);
			
			
			//3.将数据存储到数据库
			Connection con = JDBCUtilsConnection.getConnection();
			String sql = "INSERT INTO muser VALUES(?,?,?,?)";
			String[] parms = {user.getUid(),user.getUsername(),user.getPassword(),user.getPhone()};
			QueryRunner qr = new QueryRunner();
			try {
				int update = qr.update(con,sql,parms);
				if(update == 1) {
					System.out.println("注册成功");
				}else {
					System.out.println("注册失败");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			response.getWriter().write("注册成功,即将跳转到登陆界面");
			//跳转到登陆界面,重定向
			response.setHeader("refresh", "3;url=/28-Mystore/login.html");
		}else {
			response.getWriter().write("验证码错误");
			response.setHeader("refresh", "2;url=/28-Mystore/regist.html");
		}
	}
}
