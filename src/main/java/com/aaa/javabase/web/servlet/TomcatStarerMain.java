package com.aaa.javabase.web.servlet;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 参考：https://mp.weixin.qq.com/s/9CF2PqI_QIh_vNf8TWUsbA
 *
 * @author liuzhen.tian
 * @version 1.0 TomcatStarerMain.java  2024/11/19 20:31
 */
public class TomcatStarerMain {
    public static void main(String[] args) {
        try {
            // http://localhost:8080/index/hello
            Tomcat tomcat =new Tomcat();
            tomcat.getConnector();
            tomcat.getHost();
            Context context = tomcat.addContext("/", null);
            tomcat.addServlet("/","index",new HttpServlet(){
                @Override
                protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    resp.getWriter().append("hello");
                }
            });
            context.addServletMappingDecoded("/","index");
            tomcat.init();
            tomcat.start();
        }catch (Exception e){

        }
    }
}
