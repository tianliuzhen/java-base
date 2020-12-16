package com.aaa.javabase.base.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liuzhen.tian
 * @version 1.0 BioServer.java  2020/12/16 14:47
 */
public class BioServer {
    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("连接到客户端");
            executorService.submit(new Runnable(){
                @Override
                public void run() {
                    handle(socket);
                }
            });
        }

    }

    public static void handle(Socket socket){

        System.out.println("id:"+Thread.currentThread().getId());
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true){
                int read = inputStream.read(bytes);
                if (read != -1){
                    System.out.println("读取客户端数据"+new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}
