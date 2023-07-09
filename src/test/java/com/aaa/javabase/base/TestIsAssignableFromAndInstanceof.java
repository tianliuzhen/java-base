package com.aaa.javabase.base;

/**
 * @author liuzhen.tian
 * @version 1.0 TestIsAssignableFromAndInstanceof.java  2023/7/9 22:57
 */
public class TestIsAssignableFromAndInstanceof {
    // 父类
    static class AfParent {

    }

    // 子类
    static class AfChildren extends AfParent {

    }

    public static void main(String[] args) {
        // 比较相同类：= true
        System.out.println(AfChildren.class.isAssignableFrom(AfChildren.class));
        // 左类是子类，右类是父类 = false
        System.out.println(AfChildren.class.isAssignableFrom(AfParent.class));
        // 左类是父类，右类是子类 = false
        System.out.println(AfParent.class.isAssignableFrom(AfChildren.class));
        /**
         * isAssignableFrom 结论：
         *  左类.class.isAssignableFrom(右类.class)
         *  isAssignableFrom 用于判断 右类是否是左类的子类（包含当前类）
         */


        System.out.println("==========isAssignableFrom 区别 instanceof=========");

        AfChildren afChildren = new AfChildren();
        AfParent afParent = new AfParent();
        System.out.println(afChildren instanceof AfChildren);
        System.out.println(afChildren instanceof AfParent);
        System.out.println(afParent instanceof AfChildren);

        /**
         * instanceof 结论：
         *  左类实例 instanceof 右类实例
         *  instanceof 用于判断 左类实例是否是右类的子类（包含当前类实例）
         */

        // ==================== 对比说明 ==================
        /**
         * 1、 isAssignableFrom 用于比较class ，instanceof 用于比较 类实例
         * 2、 比较的含义相反，isAssignableFrom 用于比较：右类是否是左类的子类，
         *      而 instanceof 用于比较：左类实例是否是右类的实例 子类
         * 3、 isAssignableFrom 是native方法，而 instanceof 是jvm的 内置运算符
         */
    }
}
