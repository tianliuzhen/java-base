package com.aaa.javabase.base.objectOriented.javaExtends.newAnimals;

import com.aaa.javabase.base.objectOriented.javaExtends.Animal;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/18
 */
public class Mouse extends Animal {
    /**
     * 子类是不继承父类的构造器（构造方法或者构造函数）的，它只是调用（隐式或显式）。
     * 如果父类的构造器带有参数，则必须在子类的构造器中显式地通过 super 关键字调用父类的构造器并配以适当的参数列表。
     * 如果父类构造器没有参数，则在子类的构造器中不需要使用 super 关键字调用父类构造器，系统会自动调用父类的无参构造器。
     * 所以这里需要  显示的调用
     */
    public Mouse(String myName, int myid) {
        super(myName, myid);
    }
    @Override
    public void eat() {
        System.out.println("老鼠重写自己吃的方法！");
    }
    public void eatTest() {
        this.eat();   // this 调用自己的方法
//        super.eat();  // super 调用父类方法
    }
}
