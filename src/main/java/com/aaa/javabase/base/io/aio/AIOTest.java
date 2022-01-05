package com.aaa.javabase.base.io.aio;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * @author liuzhen.tian
 * @version 1.0 AIOTest.java  2020/12/16 14:58
 */
public class AIOTest {

    // @Test
    public void testServer() throws IOException, InterruptedException {
        SimpleServer server = new SimpleServer(7788);

        Thread.sleep(10000);
    }

    // @Test
    public void testClient() throws IOException, InterruptedException, ExecutionException {
        SimpleClient client = new SimpleClient("localhost", 7788);
        client.write((byte) 11);
    }

    public static void main(String[] args) {
        int n = "s2时候".hashCode();
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(15));

        System.out.println(15&n);

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("key", "123");

    }

}
