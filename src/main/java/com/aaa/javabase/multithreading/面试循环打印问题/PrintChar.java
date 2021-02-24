package com.aaa.javabase.multithreading.面试循环打印问题;

/**
 * 创建两个线程，其中一个输出ABCD~Z，另外一个输出abcd~z。输出格式要求：Aa Bb Cc Dd
 * @author liuzhen.tian
 * @version 1.0 PrintChar.java  2020/12/21 11:54
 */
class PrintChar{
    private int flag = 1;
    private int count = 1;
    /**
     *  输出小写字母
     */
    public synchronized void printA()  {
        while(flag != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print((char)(count-1+'a'));
        flag = 2;
        notify();
    }
    /**
     *  输出大写字母
     */
    public synchronized void printB(){
        while(flag != 2){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print((char)(count-1+'A')+" ");
        count++;
        flag = 1;
        notify();
    }
    public static class Test{
        public static void main(String[] args) {
            PrintChar print = new PrintChar();
            new Thread(()->{
                for(int i = 0;i<26;i++){
                    print.printA();
                }
            }).start();
            new Thread(()->{
                for(int i = 0;i<26;i++){
                    print.printB();
                }
            }).start();
        }
    }
}

