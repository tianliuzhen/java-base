package com.aaa.javabase.util;

import com.aaa.javabase.util.SftpClient;
import com.jcraft.jsch.SftpException;

import java.io.*;

/**
 * @author liuzhen.tian
 * @version $ Id: UploadFileBySftp.java, v 0.1 2020/7/1 10:08 liuzhen.tian Exp $
 */

// @SpringBootTest
public class UploadFileBySftp {

    public static final String READ_PATH = System.getProperty("user.dir")+ "\\src\\test\\resources\\test";

    // @Test
    public void upload() throws SftpException, FileNotFoundException {
        SftpClient sftpClient = new SftpClient("",22,"","");
        sftpClient.login();

        File file = new File(READ_PATH);
        InputStream in = new FileInputStream(file);
        //创建临时目录
        boolean dir = sftpClient.makeDir("file_temp_20200701");
        if (!dir){
            return;
        }
        sftpClient.upload("/tmp/fileExport/","file_temp_20200701","file_temp_20200701.txt",in);
        //判断是否上传成功
        boolean file_temp_20200701 = sftpClient.existFile("/tmp/fileExport/file_temp_20200701", "file_temp_20200701.txt");
        System.out.println(file_temp_20200701);
    }

    // @Test
    public  void createFile() throws FileNotFoundException {
        File file = new File(READ_PATH);
        if (file.exists()) {
            System.out.println("File already exists");
            System.exit(1);
        }
        PrintWriter output = new PrintWriter(file);
        try  {
            for (int i = 100000; i > 0; i--) {
                output.print("创建sftp临时文件： ");
                output.println(i+";");
            }
        }finally {
            output.close();
        }
    }

    public static void main(String[] args) {
        String property1 = System.getProperty("user.dir");
        String property = property1+ "\\app\\test\\src\\test\\java\\com\\galaxy\\gcreditbatch\\test\\biz\\impl\\file";
        System.out.println(property);
    }
}
