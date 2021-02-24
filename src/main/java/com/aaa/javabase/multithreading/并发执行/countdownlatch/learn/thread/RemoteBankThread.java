package com.aaa.javabase.multithreading.并发执行.countdownlatch.learn.thread;



public class RemoteBankThread extends BaseCheckThread {

    public RemoteBankThread(int uid) {
        super(uid);
    }

    @Override
    public Boolean call() throws Exception {
        boolean flag;

        System.out.println("银行信用 - 验证开始");
        try {
            Thread.sleep(3000);
            flag = true;
        } catch (InterruptedException e) {
            System.out.println("银行信用 - 验证终止");
            return false;
        }

        if(!flag){
            System.out.println("银行信用 - 验证成功");
            return true;
        }
        else {
            System.out.println("银行信用 - 验证失败");
            return false;
        }
    }
}
