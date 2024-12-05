package com.aaa.javabase.web.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletContextInitializerBeans;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.util.Collection;

/**
 * @author liuzhen.tian
 * @version 1.0 WebMainApplication.java  2024/11/19 21:31
 */
// @SpringBootApplication()
// @EnableScheduling
public class WebMainApplication {
    public static void main(String[] args) {
        String[] newArgs = args.clone();
        int defaultPort = 8088;
        boolean needChangePort = false;
        if (isPortInUse(defaultPort)) {
            newArgs = new String[args.length + 1];
            System.arraycopy(args, 0, newArgs, 0, args.length);
            newArgs[newArgs.length - 1] = "--server.port=9090";
            needChangePort = true;
        }
        ConfigurableApplicationContext run = SpringApplication.run(WebMainApplication.class, newArgs);
        if (needChangePort) {
            String command = String.format("lsof -i :%d | grep LISTEN | awk '{print $2}' | xargs kill -9", defaultPort);
            try {
                Runtime.getRuntime().exec(new String[]{"sh", "-c", command}).waitFor();
                while (isPortInUse(defaultPort)) {
                }
                ServletWebServerFactory webServerFactory = getWebServerFactory(run);
                ((TomcatServletWebServerFactory) webServerFactory).setPort(defaultPort);
                WebServer webServer = webServerFactory.getWebServer(invokeSelfInitialize(((ServletWebServerApplicationContext) run)));
                webServer.start();

                ((ServletWebServerApplicationContext) run).getWebServer().stop();
            } catch (IOException | InterruptedException ignored) {
            }
        }

    }

    private static ServletContextInitializer invokeSelfInitialize(ServletWebServerApplicationContext context) {
        try {
            Method method = ServletWebServerApplicationContext.class.getDeclaredMethod("getSelfInitializer");
            method.setAccessible(true);
            return (ServletContextInitializer) method.invoke(context);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

    private static boolean isPortInUse(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false;
        } catch (IOException e) {
            return true;
        }
    }

    protected static Collection<ServletContextInitializer> getServletContextInitializerBeans(ConfigurableApplicationContext context) {
        return new ServletContextInitializerBeans(context.getBeanFactory());
    }


    private static ServletWebServerFactory getWebServerFactory(ConfigurableApplicationContext context) {
        String[] beanNames = context.getBeanFactory().getBeanNamesForType(ServletWebServerFactory.class);

        return context.getBeanFactory().getBean(beanNames[0], ServletWebServerFactory.class);
    }
}
