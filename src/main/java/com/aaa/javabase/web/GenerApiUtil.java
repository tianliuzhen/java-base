package com.aaa.javabase.web;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * 参考官网：https://japidocs.agilestudio.cn/#/zh-cn/?id=%e5%bf%ab%e9%80%9f%e5%bc%80%e5%a7%8b
 * description: JApiDocs 自动生成接口文档
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/6/22
 */
public class GenerApiUtil {
    public static void main(String[] args) {
        DocsConfig config = new DocsConfig();
        config.setProjectPath("F:\\WorkSpace\\MyGithub\\java-base"); // 项目根目录
        config.setProjectName("ProjectName"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("your api docs path"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        Docs.buildHtmlDocs(config); // 执行生成文档
    }
}
