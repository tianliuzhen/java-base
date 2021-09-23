参考：https://www.cnblogs.com/fingerboy/p/9948736.html
## 1、##########创建任务##########
###方法：
    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier){..}
    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier,Executor executor){..}
    public static CompletableFuture<Void> runAsync(Runnable runnable){..}
    public static CompletableFuture<Void> runAsync(Runnable runnable,Executor executor){..}
注：
    如果有多线程的基础知识,我们很容易看出,run开头的两个方法,用于执行没有返回值的任务,因为它的入参是Runnable对象
    而supply开头的方法显然是执行有返回值的任务了。
    至于方法的入参,如果没有传入Executor对象将会使用ForkJoinPool.commonPool() 作为它的线程池执行异步代码

## 2、############ 获取执行结果##########
###方法：
    V get();
    V get(long timeout,Timeout unit);
    T getNow(T defaultValue);
    T join();
注：
    上面两个方法是Future中的实现方式,get()会堵塞当前的线程,这就造成了一个问题,如果执行线程迟迟没有返回数据,get()会一直等待下去,
    因此,第二个get()方法可以设置等待的时间.
    getNow()方法比较有意思,表示当有了返回结果时会返回结果,如果异步线程抛了异常会返回自己设置的默认值.
    join()和 get() 功能作用一样。
    join()方法抛出的是uncheck异常（即未经检查的异常),不会强制开发者抛出
    get()方法抛出的是经过检查的异常，ExecutionException, InterruptedException 需要用户手动处理（抛出或者 try catch）
    
## 3、############# 常用then方法操作
  
### thenApply 方法：
    public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
    public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
    public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
注：
    thenApply()对比thenAccept()只是一个无参数，一个有参数。
    当前任务正常完成以后执行，当前任务的执行的结果会作为下一任务的输入参数,有返回值
    场景:多个任务串联执行,下一个任务的执行依赖上一个任务的结果,每个任务都有输入和输出

### thenAccept 方法：=> 【thenApply 的 无返回值版本 】
    public CompletionStage<Void> thenAccept(Consumer<? super T> action);
    public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
    public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor);
注：
    当前任务正常完成以后执行,当前任务的执行结果可以作为下一任务的输入参数,无返回值.
    场景:执行任务A,同时异步执行任务B,待任务B正常返回之后,用B的返回值执行任务C,任务C无返回值
    
### thenCombine 方法：
    thenCombine(..)  thenAcceptBoth(..)  runAfterBoth(..)
    public <U,V> CompletableFuture<V>  thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)
    ...
    ...
注：
    结合两个CompletionStage的结果，进行转化后返回
    场景:需要根据商品id查询商品的当前价格,分两步,查询商品的原始价格和折扣,这两个查询相互独立,当都查出来的时候用原始价格乘折扣,算出当前价格. 使用方法:thenCombine(..)

### thenAcceptBoth 方法： =》 【thenCombine 的 无返回值版本 】
方法：
    public <U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action) 
    ...
    ...
注：
    thenCombine(..)是结合两个任务后的返回值进行转化后再返回,那如果不需要返回呢,那就需要thenAcceptBoth(..)
    
### runAfterBoth 方法： 
方法：
    public <U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other,BiConsumer<? super T, ? super U> action) 
    ...
    ...
注：
    thenCombine(..)是结合两个任务的返回值进行转化后再返回,那如果不需要返回呢,那就需要thenAcceptBoth(..),
    同理,如果连两个任务的返回值也不关心呢,那就需要runAfterBoth了

### thenCompose 方法： 

方法：
    public <U> CompletableFuture<U>     thenCompose(Function<? super T,? extends CompletionStage<U>> fn)
    ...
    ...
注：   
    功能:这个方法接收的输入是当前的CompletableFuture的计算值，返回结果将是一个新的CompletableFuture
　　这个方法和thenApply非常像,都是接受上一个任务的结果作为入参,执行自己的操作,然后返回.那具体有什么区别呢?
　　thenApply():它的功能相当于将CompletableFuture<T>转换成CompletableFuture<U>,改变的是同一个CompletableFuture中的泛型类型
　　thenCompose():用来连接两个CompletableFuture，返回值是一个新的CompletableFuture

## 4、##################常用 Either 方法操作 ##########
### 方法 applyToEither：
    public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other,Function<? super T, U> fn);
    public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn);
    public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T, U> fn,Executor executor);
注：  
    功能:执行两个CompletionStage的结果,那个先执行完了,就是用哪个的返回值进行下一步操作
    场景:假设查询商品a,有两种方式,A和B,但是A和B的执行速度不一样,我们希望哪个先返回就用那个的返回值.    
    
### 方法 acceptEither：  =》 【applyToEither 的 无返回值版本 】
    public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) 
    ...
    ...
    
### 方法 runAfterEither：
    public CompletableFuture<Void> runAfterEither(CompletionStage<?> other,Runnable action) 
    ...
    ...
注：
    顾名思义哪个先执行完了，再进行触发一个新的 Runable去执行

## 5、##################常用 exceptionally 方法操作 ##########
方法：
    public CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn);
注：
    功能:当运行出现异常时,调用该方法可进行一些补偿操作,如设置默认值.
    场景:异步执行任务A获取结果,如果任务A执行过程中抛出异常,则使用默认值100返回.    

## 6、##################常用 whenComplete 方法操作 ##########
方法：
    public CompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action);
注：
    功能:当CompletableFuture的计算结果完成，或者抛出异常的时候，都可以进入whenComplete方法执行
        
## 7、##################常用 handle 方法操作 ##########
方法：
    public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
    public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
    public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);
注：
    　功能:当CompletableFuture的计算结果完成，或者抛出异常的时候，可以通过handle方法对结果进行处理
      跟 whenComplete 比较像都是对结果进行处理,handle有返回值,whenComplete没有返回值
