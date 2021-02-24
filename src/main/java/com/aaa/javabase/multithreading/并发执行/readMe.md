场景：
    描述：同步项目数据接口访问太慢。需要 5秒左右
    分析：内部调用了，同步招聘、同步立项、同步 po/rt/po、导致总得速度过慢。
    解决方案：
        
    多线程并发
    
执行方式：
    1、countDownLatch  线程计数器，每次使用  countDown自减操作。最后  await 等待（可设置超时）
    2、cyclicBarrier   循环屏障，每次使用 barrier.await();
    3、使用 future的 get() 阻塞
    4、采用jdk8的 CompletableFuture 
     
    
