package com.aaa.javabase.util.jdbc;

import java.sql.*;

/**
 * @author liuzhen.tian
 * @version 1.0 MysqlJdbcDemo.java  2025/1/10 22:09
 */
public class MysqlJdbcDemo {
    public static void main(String[] args) {
        mysqlJdbc();
    }

    /**
     * 大坑：
     * jdbc:h2:mem:test 指的是在同一个 jvm 环境中才能加载到。
     */
    public static void mysqlJdbc() {
        // String url = "jdbc:h2:mem:test"; // 内存模式
        // String url = "jdbc:mysql://127.0.0.1:3301/master?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowMultiQueries=true"; // tcp远程连接
        String url = "jdbc:mysql://127.0.0.1:3301/slave?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowMultiQueries=true"; // tcp远程连接
        String user = "root"; // 默认用户名
        String password = "123456"; // 默认密码为空
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // 加载 mysql 驱动8
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 加载 mysql 驱动5
            // Class.forName("com.mysql.jdbc.Driver");

            // 建立连接
            conn = DriverManager.getConnection(url, user, password);

            // 创建表格（如果尚不存在）
            stmt = conn.prepareStatement("SELECT * FROM emp WHERE user_name <> ?");
            stmt.setString(1, "");  // 设置第一个 ? 的值为 userName
            System.out.println("SQL: " + stmt.toString());

            // 查询数据
            rs = stmt.executeQuery();

            while (rs.next()) {
                String id  = rs.getString("user_name");
                String name = rs.getString("deptno");
                int age = rs.getInt("manager_id");
                System.out.print("user_name: " + id);
                System.out.print(", deptno: " + name);
                System.out.println(", manager_id: " + age);
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
