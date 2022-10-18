package com.aaa.javabase.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author liuzhen.tian
 * @version 1.0 ReadResourceFile.java  2022/9/18 12:06
 */

@SpringBootTest
public class ReadResourceFile {

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    public void main() throws IOException {
        /**
         * 前四种方法在开发环境(IDE中)和生产环境(linux部署成jar包)都可以读取到，
         * 第五种只有开发环境时可以读取到，打成jar包就不行。
         * 主要原因是springboot内置tomcat，打包后是一个jar包，
         * 因此通过文件读取获取流的方式行不通，因为无法直接读取压缩包中的文件，
         * 读取只能通过流的方式读取。
         */
        InputStream inputStream;
        // 方式一：使用org.springframework.core.io.ClassPathResource，各种环境都能读取。（通用）
        ClassPathResource classPathResource = new ClassPathResource("json/config.json");
        inputStream = classPathResource.getInputStream();

        // 方式二
        inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("json/config.json");

        // 方式三
        inputStream = ReadResourceFile.class.getResourceAsStream("json/config.json");

        // 方式四：结合spring注解，使用org.springframework.core.io.ResourceLoader;类的注解。（通用）
        inputStream = resourceLoader.getResource("classpath:config.json").getInputStream();

        // 方式五：使用org.springframework.util.ResourceUtils，读取。在linux环境中无法读取。（不通用）
        File file = ResourceUtils.getFile("classpath:config.json");
        inputStream = new FileInputStream(file);
    }
}
