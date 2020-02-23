package com.aaa.javabase.objectOriented.overrideAndOverload;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2020/1/18
 */

class Animal{
    public void move(){
        System.out.println("动物可以移动");
    }
}

class Dog extends Animal{
    @Override
    public void move(){
        System.out.println("狗可以跑和走");
    }
    public void bark(){
        System.out.println("狗可以吠叫");
    }

    /**
     * 重载
     * @param str
     * @param ints
     * @return
     */
    public String bark(String str,Integer ints){
        System.out.println("狗可以吠叫");
        return "";
    }
    public String bark(Integer ints,String str){
        System.out.println("狗可以吠叫");
        return "";
    }
}

public class TestDog{
    public static void main(String args[]){
        // Animal 对象
        Animal a = new Animal();
        // Dog 对象
        Animal b = new Dog();

        a.move();// 执行 Animal 类的方法

        b.move();//执行 Dog 类的方法

        // 父类执行子类特有的方法需要像下转型
        ((Dog) b).bark();

        /**
         * 总结
         *
         * 方法的重写(Overriding)和重载(Overloading)是java多态性的不同表现，重写是父类与子类之间多态性的一种表现，重载可以理解成多态的具体表现形式。
         *
         *     (1)方法重载是一个类中定义了多个方法名相同,而他们的参数的数量不同或数量相同而类型和次序不同,则称为方法的重载(Overloading)。
         *     (2)方法重写是在子类存在方法与父类的方法的名字相同,而且参数的个数与类型一样,返回值也一样的方法,就称为重写(Overriding)。
         *     (3)方法重载是一个类的多态性表现,而方法重写是子类与父类的一种多态性表现。
         */

    }
}

