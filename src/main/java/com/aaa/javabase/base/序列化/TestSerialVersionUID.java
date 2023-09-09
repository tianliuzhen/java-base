package com.aaa.javabase.base.序列化;

import java.io.*;

/**
 * @author liuzhen.tian
 * @version 1.0 TestSerialVersionUID.java  2023/9/9 19:28
 */
public class TestSerialVersionUID {

    public static final String BEAN_TXT = "F:\\WorkSpace\\MyGithub\\java-base\\src\\main\\java\\com\\aaa\\javabase\\base\\序列化\\SvBean.txt";

    public static void main(String[] args) {
        /**
         * 测试流程
         * 1、第一次先执行  serial();
         * 2、修改 SvBean 属性增加一个字段，或者修改一个字段类型
         * 3、执行 deserial(); 会抛出异常【java.io.InvalidClassException: com.aaa.javabase.base.序列化.SvBean; local class incompatible: stream classdesc serialVersionUID = -4197147695838111201, local class serialVersionUID = 9078139409403258831】
         * 结论
         * 4、指定serialVersionUID为指定数值后，即使对象的属性发生变更，再次反序列化也不影响
         */
        serial();
        deserial();


        /**
         * serialVersionUID的生成
         * serialVersionUID是为了兼容不同版本的，
         * 在JDK中，可以利用JDK的bin目录下的serialver.exe工具产生这个serialVersionUID，SvBean.class，
         * 执行命令：serialver Student
         *
         * IDEA生成实际上也是调用这个命令
         */
        ObjectStreamClass c = ObjectStreamClass.lookup(SvBean.class);
        /**
         * 源码来看，serialVersionUID的计算逻辑,
         * 基本是将类名，属性名，属性修饰符，继承的接口，属性类型，名称，方法，静态代码块等等…这些都考虑进去了，
         * 都写到一个DataOutputStream中，然后再做hash运算
         */
        long serialID = c.getSerialVersionUID();
        System.out.println(serialID);
    }

    // 序列化
    private static void serial() {
        SvBean svBean = new SvBean(9, "Mike");//注意这里写入的值
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(BEAN_TXT);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(svBean);
            objectOutputStream.flush();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // 反序列化
    private static void deserial() {
        try {
            FileInputStream fis = new FileInputStream(BEAN_TXT);
            ObjectInputStream ois = new ObjectInputStream(fis);
            SvBean svBean = (SvBean) ois.readObject();
            ois.close();
            System.out.println(svBean.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
