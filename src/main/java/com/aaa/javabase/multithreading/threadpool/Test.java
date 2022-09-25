package com.aaa.javabase.multithreading.threadpool;

import com.aaa.javabase.base.reflect.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author liuzhen.tian
 * @version 1.0 Test.java  2020/12/16 19:05
 */
public class Test {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person person = new Person("tom",1);
        Person person2 = new Person("tom",1);
        System.out.println(person.hashCode()  +"=="+person2.hashCode());
        System.out.println(person.equals(person2));

        Integer num = 127;
        Integer num2 = 127;
        System.out.println(num == num2);


    }

}

class MyFutureTask extends FutureTask<String> {

    public MyFutureTask(Callable<String> callable) {
        super(callable);
    }

    @Override
    protected void done() {
        try {
            System.out.println(get() + " 线程执行完毕！~");
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

class Task implements Callable<String> {

    @Override
    public String call() throws Exception {
        Random rand = new Random();
        TimeUnit.SECONDS.sleep(rand.nextInt(12));
        return Thread.currentThread().getName();
    }
}
