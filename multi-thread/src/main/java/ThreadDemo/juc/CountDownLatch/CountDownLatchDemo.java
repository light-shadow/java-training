package main.java.ThreadDemo.juc.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @description: countdownlatchdemo
 * @author: general
 * @version: 1.0
 * @create: 2019-09-11 17:21
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(()->{
            try {
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                Thread.sleep(3000);
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                System.out.println(Thread.currentThread().getName() +" start "+countDownLatch.toString());
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() +" end "+countDownLatch.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-1").start();

        new Thread(()->{
            try {
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                Thread.sleep(3000);
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                System.out.println(Thread.currentThread().getName()+" start "+countDownLatch.toString());
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+" end "+countDownLatch.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-2").start();

        new Thread(()->{
            try {
                System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
                Thread.sleep(3000);
                System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                System.out.println(Thread.currentThread().getName()+" start "+countDownLatch.toString());
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+" end "+countDownLatch.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread-3").start();

        try {
            System.out.println("等待2个子线程执行完毕...");
            countDownLatch.await();
            System.out.println("两个子线程执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
