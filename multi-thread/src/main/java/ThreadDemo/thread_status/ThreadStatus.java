package main.java.ThreadDemo.thread_status;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * @description: 线程状态
 * @author: general
 * @version: 1.0
 * @create: 2019-09-01 21:59
 **/
public class ThreadStatus {

    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Time_Waiting").start();

        new Thread(()->{
            synchronized (ThreadStatus.class){
                try {
                    ThreadStatus.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Waiting").start();

        new Thread(new BlockedDemo(), "BlockedDemo-1").start();
        new Thread(new BlockedDemo(), "BlockedDemo-1").start();
    }

    static class BlockedDemo extends Thread{
        @Override
        public void run() {
            synchronized (BlockedDemo.class){
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
