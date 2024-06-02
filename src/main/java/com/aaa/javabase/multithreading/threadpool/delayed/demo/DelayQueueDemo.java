package com.aaa.javabase.multithreading.threadpool.delayed.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue = BlockingQueue + PriorityQueue + Delayed
 * DelayQueue的关键元素BlockingQueue、PriorityQueue、Delayed。
 * DelayQueue是一个使用优先队列（PriorityQueue）实现的BlockingQueue，优先队列的比较基准值是时间。
 * <p>
 * 延迟队列场景：
 * a) 关闭空闲连接。服务器中，有很多客户端的连接，空闲一段时间之后需要关闭之。
 * b) 缓存。缓存中的对象，超过了空闲时间，需要从缓存中移出。
 * c) 任务超时处理。在网络协议滑动窗口请求应答式交互时，处理超时未响应的请求。
 *
 * @author liuzhen.tian
 * @version 1.0 DelayQueueDemo.java  2021/12/17 21:28
 */
public class DelayQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Order Order1 = new Order("Order1", 5, TimeUnit.SECONDS);
        Order Order2 = new Order("Order2", 10, TimeUnit.SECONDS);
        Order Order3 = new Order("Order3", 15, TimeUnit.SECONDS);
        DelayQueue<Order> delayQueue = new DelayQueue<>();
        delayQueue.put(Order1);
        delayQueue.put(Order2);
        delayQueue.put(Order3);

        System.out.println("订单延迟队列开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        while (delayQueue.size() != 0) {
            /**
             * 取队列头部元素是否过期
             */
            Order task = delayQueue.poll();
            if (task != null) {
                System.out.format("订单:{%s}被取消, 取消时间:{%s}\n", task.name, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            Thread.sleep(1000);
        }
        System.out.println("执行结束");
    }
}
