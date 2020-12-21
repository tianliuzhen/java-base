package com.aaa.javabase.multithreading.面试;

/**
 * @author liuzhen.tian
 * @version 1.0 PrintOddOrEven.java  2020/12/21 23:03
 */
public class PrintOddOrEven {
    private int flag = 1;
    private int oddSum = 1;
    private int evenSum = 2;
    /**
     *  输出奇数
     */
    public synchronized void printOdd()  {
        while(flag != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(oddSum + " ");
        oddSum += 2;
        flag = 2;
        notify();
    }
    /**
     *  输出偶数
     */
    public synchronized void printEven() {
        while (flag != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(evenSum + " ");
        evenSum += 2;
        flag = 1;
        notify();
    }

    public static void main(String[] args) {
        PrintOddOrEven print = new PrintOddOrEven();
        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                print.printOdd();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 50; i++) {
                print.printEven();
            }
        }).start();
    }
}
