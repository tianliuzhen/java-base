package com.aaa.javabase.util;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Author: xixuan.cw
 * @Date: 2020/6/29 2:11 下午
 * 压缩和解压缩文件工具类
 */
public final class GzipUtil {

    /**
     * 压缩文件到.gz
     *
     * @param inFileName
     */
    public static void gzipFile(String inFileName, String outFileName) {
        try (FileInputStream in = new FileInputStream(inFileName); GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(outFileName))) {
            // 读取文件
            // 要写入的文件
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解压缩文件
     *
     * @param sourcedir
     * @param suffix
     */
    public static String unGzipFile(String sourcedir, String suffix) {
        FileInputStream fin = null;
        GZIPInputStream gzin = null;
        FileOutputStream fout = null;
        try {
            //建立gzip压缩文件输入流
            fin = new FileInputStream(sourcedir);
            //建立gzip解压工作流
            gzin = new GZIPInputStream(fin);

            String outputFileName = sourcedir;
            if (sourcedir.endsWith(".gz")) {
                outputFileName = sourcedir.substring(0, sourcedir.lastIndexOf("."));
            }

            // 如果文件有指定的后缀，以指定后缀解析文件
            if (StringUtils.isNoneBlank(suffix) && !outputFileName.trim().endsWith(suffix)) {
                outputFileName = outputFileName + suffix;
            }

            //建立解压文件输出流
            fout = new FileOutputStream(outputFileName);
            byte[] buf = new byte[1024];
            int offset;
            while ((offset = gzin.read(buf)) > 0) {
                fout.write(buf, 0, offset);
            }
            return outputFileName;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException e) {
                }
            }
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                }
            }
            if (gzin != null) {
                try {
                    gzin.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static void main(String[] args) {
//        String filePath = "/tmp/my/credit_limit_adjust_apply__20200511";
//        unGzipFile(filePath, ".txt");

//        String source = "/tmp/my/credit_limit_adjust_apply__20200511.txt";
        String outFileName = "/tmp/my/test";
//        gzipFile(source, outFileName);

        unGzipFile(outFileName, ".txt");
    }
}
