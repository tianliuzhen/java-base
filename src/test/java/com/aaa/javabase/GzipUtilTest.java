package com.aaa.javabase;


import com.aaa.javabase.util.GzipUtil;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author liuzhen.tian
 * @version $ Id: GzipUtilTest.java, v 0.1 2020/7/1 18:08 liuzhen.tian Exp $
 */
@SpringBootTest
public class GzipUtilTest  {

    public static final String READ_PATH = GzipUtilTest.class.getClassLoader().getResource("test/testFile.txt").getPath();

    public static final String WRITE_PATH = System.getProperty("user.dir")+ "\\src\\test\\resources\\test\\testFile.zip";


    // @Test
    public void gzipFile(){
        /**
         * 读取文件的路径
         * 压缩后的路径
         */
        GzipUtil.gzipFile(READ_PATH,WRITE_PATH);
    }

    // @Test
    public void unGzipFile(){
        /**
         * 读取压缩文件的路径
         * 解压后的追加的后缀  默认是 xxx.zip  ,若带参数（"txt"），则最终生成 =》 xxx.ziptxt
         *
         */
        GzipUtil.unGzipFile(WRITE_PATH,"txt");
    }


}
