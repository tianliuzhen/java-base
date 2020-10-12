package com.aaa.javabase.base.protecteds.demo2.p;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2020/10/12 15:00
 */
public class Test extends MyObject {

    public static void main(String[] args) {
        // case 1:
        MyObject myObject = new MyObject();
        //myObject.clone();// 编译错误
        /**
         * 对于  case 1:
         * clone()方法来自于类MyObject本身，因此其可见性为包p及MyObject的子类，
         * 虽然Test是MyObject的子类，但在Test中不能访问父类MyObject的protected方法clone()，因此编译不通过;
         */


        // case 2:
        Test test = new Test();
        test.clone();
        /**
         *对于 case 2:
         * 由于在Test中访问的是其本身实例的从父类MyObject继承来的的clone()，因此编译通过。所以在这里，就很好地阐述了上面所给的第二条结论：
         */

    }
}
