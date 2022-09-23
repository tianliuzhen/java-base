package com.aaa.javabase.pattern.structure.sharedUnit.demo1;

/**
 * @author liuzhen.tian
 * @version 1.0 TestMain.java  2022/9/23 22:14
 */
public class TestMain {
    public static void main(String[] args) {
        /**
         * 这里的内部状态是：价格
         * 外部状态是：出发地和到达地
         */
        ITicket ticket = TicketSharedFactory.getTicket("杭州", "周口");
        ticket.showTicketInfo("一等座");

        ITicket ticket2 = TicketSharedFactory.getTicket("杭州", "郑州");
        ticket2.showTicketInfo("二等座");
    }
}
