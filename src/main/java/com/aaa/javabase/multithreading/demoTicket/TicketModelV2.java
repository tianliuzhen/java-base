package com.aaa.javabase.multithreading.demoTicket;

/**
 * @author liuzhen.tian
 * @version 1.0 TicketModelV2.java  2020/9/17 9:57
 */
public class TicketModelV2 {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(new Ticket("name"+i)).start();
        }
    }

    static class Ticket implements Runnable{
        private static int num = 20;
        private String name;

        public Ticket(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true){
                if(num<=0){
                    System.out.println(name + ":余票不足，购买失败");
                    break;
                }else {
                    System.out.println("名称" + name + "的线程ID是：" + Thread.currentThread().getId());
                    System.out.println(name + ":您成功购买到一张票，余票剩余" + (--num));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
