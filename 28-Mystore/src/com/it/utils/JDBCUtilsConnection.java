package com.it.utils;
//:����һ���Զ��������ݿ����ӵ���

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtilsConnection {
    private static Connection con;

    public JDBCUtilsConnection() {
    }

    public static Connection getConnection() {
        return con;
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mystore?useUnicode=true&characterEncoding=utf-8";
            String name = "root";
            String password = "root";
            con = DriverManager.getConnection(url, name, password);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("���ݿ�����ʧ��!"); 
        }
    }
}
