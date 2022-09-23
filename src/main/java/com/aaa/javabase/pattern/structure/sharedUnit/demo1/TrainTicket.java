package com.aaa.javabase.pattern.structure.sharedUnit.demo1;

import java.util.Random;

/**
 * @author liuzhen.tian
 * @version 1.0 TrainTicketServiceImpl.java  2022/9/23 22:00
 */
public class TrainTicket implements ITicket {
    private double price;
    private String from;
    private String to;

    public TrainTicket(String from, String to) {
        this.price = new Random().nextInt(500);
        this.from = from;
        this.to = to;
    }

    @Override
    public void showTicketInfo(String type) {
        System.out.println(String.format("始发站：%s ===》到达站 %s  价格：%s 元  类型：%s",
                from, to, price, type));
    }
}
