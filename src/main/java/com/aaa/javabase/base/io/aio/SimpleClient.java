package com.aaa.javabase.base.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author liuzhen.tian
 * @version 1.0 SimpleClient.java  2020/12/16 14:57
 */
public class SimpleClient {

    private AsynchronousSocketChannel client;

    public SimpleClient(String host, int port) throws IOException, InterruptedException, ExecutionException {
        this.client = AsynchronousSocketChannel.open();
        Future<?> future = client.connect(new InetSocketAddress(host, port));
        future.get();
    }

    public void write(byte b) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        byteBuffer.put(b);
        byteBuffer.flip();
        client.write(byteBuffer);
    }

}
