package main.java.ThreadDemo.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: ReentrantLock demo
 * @author: general
 * @version: 1.0
 * @create: 2019-09-09 16:21
 **/
public class AtomicDemo {

    private static int count=0;
    private static int count1=0;


    static Lock lock = new ReentrantLock();
    public static void inc(){
        lock.lock();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        lock.unlock();
    }

    public static void incWithout(){
        count1++;
    }

    public static void main(String[] args) throws InterruptedException {
//        for (int i = 0; i < 1000; i++) {
//            new Thread(()->{
//                AtomicDemo.inc();
//            }).start();
//        }
//        Thread.sleep(3000);
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                AtomicDemo.incWithout();
            }).start();
        }
        Thread.sleep(3000);
        System.out.println("result= "+ count);
        System.out.println("result= "+ count1);
    }
}
