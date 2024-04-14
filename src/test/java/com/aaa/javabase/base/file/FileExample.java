package com.aaa.javabase.base.file;

/**
 * @author liuzhen.tian
 * @version 1.0 FileExample.java  2024/4/14 13:34
 */
import java.io.File;

public class FileExample {
    public static void main(String[] args) {
        // 创建一个表示父目录的 File 对象
        File parentDirectory = new File("F:\\WorkSpace\\MyGithub\\java-base\\src\\test\\java\\com\\aaa\\javabase\\base\\file\\data");

        // 创建一个子目录或文件名的字符串
        String childName = "META-INF\\services\\com.aaa.api.InternetApi";

        // 使用父目录和子目录或文件名来创建新的 File 对象
        File newFile = new File(parentDirectory, childName);

        // 输出新创建的 File 对象的路径
        System.out.println("New File Path: " + newFile.getPath());

        // 你还可以检查这个 File 对象是否存在
        if (newFile.exists()) {
            System.out.println("The file or directory already exists.");
        } else {
            System.out.println("The file or directory does not exist.");
        }

        // 如果需要，你可以尝试创建这个目录
        if (!newFile.exists()) {
            boolean directoryCreated = newFile.mkdirs();
            if (directoryCreated) {
                System.out.println("Directory created successfully.");
            } else {
                System.out.println("Failed to create directory.");
            }
        }
    }
}
