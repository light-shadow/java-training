package main.java.ThreadDemo;

import java.util.concurrent.*;

/**
 * @description: 实现callable接口
 * @author: general
 * @version: 1.0
 * @create: 2019-09-01 21:35
 **/
public class CallableDemo implements Callable<String> {


    @Override
    public String call() throws Exception {
        int a = 1;
        int b = 2;
        System.out.println("a+b="+(a+b));;
        return "执行结果"+(a+b);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        CallableDemo callableDemo = new CallableDemo();
        Future<String> future = executorService.submit(callableDemo);
        System.out.println(future.get());
        executorService.shutdown();
    }
}
