package com.aaa.javabase.base.list.协变和逆变;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 使用extends产生协变，传入方法的参数，必须是T的类型或者子类型的集合，此时集合不能add，只能get。
 * 如果能够add，那么在调用方法内，如果我add了一个T的子类对象，
 * 这个对象类型又跟传入的list中的对象类型 不一样，那么就会导致在调用方法外，出现类型错误。
 * 这样就产生了限制生产者的作用。
 * <p>
 * 使用super产生逆变，传入方法的参数，必须是T的类型或者父类型的集合，
 * 此时集合能够add，但是get只能得到Object，在方法内，相当于生产者，
 * 只生产T类型或者子类的对象，供调用者使用。外部调用者相当于消费者，
 * 只能消费到T类型或其子类的对象。
 *
 * @author liuzhen.tian
 * @version 1.0 TestE.java  2022/6/25 13:35
 */
public class TestE {
    public static void main(String[] args) {
        /**
         * Integer 是  Number 子类
         * 因为泛型的类型是不变的：List<Integer> 却不是 List<Number>
         */
        Number number = Integer.valueOf("100");
        // List<Number> numbers = new ArrayList<Integer>(); // 编译失败

        /**
         * 如何让 List<Integer> 是 List<Number> 的子类呢？于是有了协变
         *
         * 协变：<? extends Number>
         *
         * 场景：一般用于读参数
         * 特性：可以调用 get，不能 add
         *
         */
        List<? extends Number> numbers = new ArrayList<Integer>() {{
            add(1);
        }};
        /*
         在Java中，当你声明一个类型为 List<? extends Number> 的变量时，
         你实际上是在说：“我想要一个列表，它可以包含Number或其任何子类的实例，但我不确定具体是哪一个。” 由于这种不确定性，Java编译器限制了你可以对这个列表做的操作，以防止类型不安全。
         具体来说，List<? extends Number> 是一个通配符泛型表达式，它表示列表的元素可以是Number或其任何子类。
         然而，由于你不知道具体是哪个子类（或者是Number本身），
         你不能向这个列表中添加除了null之外的任何元素。这是因为Java无法保证你添加的元素符合列表实际要求的类型。
         */
        // numbers.add(123); //编译失败

        numbers.get(0);


        /**
         * 怎么样让容器的泛型也能add呢？ 于是有了逆变
         *
         * 逆变：<? super Integer>
         * 理解：下面的 Number 成了 <? super Integer> 的子类
         *
         * 场景：一般用于写参数
         * 特性：可以add，也可以get
         */
        List<? super Integer> numbers2 = new ArrayList<Number>();
        /*
         * 当你声明一个类型为 List<? super Integer> 的变量时，你实际上是在说：“我想要一个列表，它可以包含Integer或其任何父类的实例
         * 与List<? extends Number>不同，List<? super Integer> 允许向列表中添加Integer或其父类的实例。
         * 这是因为在这个上下文中，通配符表示的是“Integer”或其父类，而不是“Number”或其子类
         * 当你声明 List<? super Integer> numbers2 时，你告诉编译器你有一个列表，其元素类型可以是Integer或其父类。
         * 因此，你可以将整数（Integer的实例）添加到这个列表中，因为整数是Integer的实例，并且Integer是Number的子类。
         * 所以，对于 List<? super Integer> numbers2 = new ArrayList<Number>(); 这段代码：
         * 虽然它声明为 ArrayList<Number>，但由于上下文的限制（List<? super Integer>），
         * 它实际上是一个可以存储Integer或其父类的列表。
         * 你可以将整数（作为Integer实例）添加到这个列表中，因为整数是Integer的一个实例，而Integer是Number的子类。
         */
        numbers2.add(123);
        // 编译器不能确定容器中是什么类型的元素
        // Number number2 = numbers2.get(0); // 编译失败
        // 返回类型是 Object类型
        Object object = numbers2.get(0);

        /**
         * 协变和逆变应用场景
         *
         * demo 1：
         * jdk：Collections.copy 【copy(List<? super T> dest, List<? extends T> src)】
         * dest写入值，必须是 src读取值的父类
         */
        // 新值 ：写入
        ArrayList<Number> dest = new ArrayList<>();
        // 资源值：读取
        ArrayList<Long> src = new ArrayList<>();
        Collections.copy(dest, src);

    }

}
