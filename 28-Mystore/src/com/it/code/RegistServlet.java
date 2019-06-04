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
		//1.�������ı���
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String checkCode = (String)this.getServletContext().getAttribute("checkCode");
		String code = request.getParameter("code");
		if(checkCode.equals(code)) {
			
			//2.�����ݴ洢��domain����
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
			
			
			//3.�����ݴ洢�����ݿ�
			Connection con = JDBCUtilsConnection.getConnection();
			String sql = "INSERT INTO muser VALUES(?,?,?,?)";
			String[] parms = {user.getUid(),user.getUsername(),user.getPassword(),user.getPhone()};
			QueryRunner qr = new QueryRunner();
			try {
				int update = qr.update(con,sql,parms);
				if(update == 1) {
					System.out.println("ע��ɹ�");
				}else {
					System.out.println("ע��ʧ��");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			response.getWriter().write("ע��ɹ�,������ת����½����");
			//��ת����½����,�ض���
			response.setHeader("refresh", "3;url=/28-Mystore/login.html");
		}else {
			response.getWriter().write("��֤�����");
			response.setHeader("refresh", "2;url=/28-Mystore/regist.html");
		}
	}
}
