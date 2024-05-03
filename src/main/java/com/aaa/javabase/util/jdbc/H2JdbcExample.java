package com.aaa.javabase.util.jdbc;

/**
 * @author liuzhen.tian
 * @version 1.0 TestH2Jdbc.java  2024/4/10 21:28
 */

import java.sql.*;

public class H2JdbcExample {
    public static void main(String[] args) {
        H2Jdbc();
    }

    /**
     * 大坑：
     * jdbc:h2:mem:test 指的是在同一个 jvm 环境中才能加载到。
     */
    public static void H2Jdbc() {
        // String url = "jdbc:h2:mem:test"; // 内存模式
        String url = "jdbc:h2:tcp://localhost/~/test"; // tcp远程连接
        String user = "root"; // 默认用户名
        String password = "123456"; // 默认密码为空
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 加载 H2 驱动（通常这一步可以省略，因为从 JDBC 4.0 开始，驱动可以自动加载）
            Class.forName("org.h2.Driver");

            // 建立连接
            conn = DriverManager.getConnection(url, user, password);

            // 创建表格（如果尚不存在）
            stmt = conn.createStatement();

            // 查询数据
            rs = stmt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                int age = rs.getInt("AGE");
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.println(", Age: " + age);
            }

            // 关闭资源
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

