package com.aaa.javabase.pattern.structure.sharedUnit.demo1;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 火车票工厂
 *
 * @author liuzhen.tian
 * @version 1.0 TicketSharedFactory.java  2022/9/23 22:03
 */
public class TicketSharedFactory {
    static Map<String, ITicket> iTicketHashMap = Maps.newHashMap();

    public static ITicket getTicket(String from, String to) {
        String position = from + "-" + to;
        ITicket iTicket = iTicketHashMap.get(position);
        if (iTicket != null) {
            return iTicket;
        }

        TrainTicket trainTicket = new TrainTicket(from, to);
        iTicketHashMap.put(position, trainTicket);
        return trainTicket;
    }
}
