package com.it.utils;
//:创建一个自动生成数据库连接的类

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
            System.out.println("数据库连接失败!"); 
        }
    }
}
