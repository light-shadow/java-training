package main.java.ThreadDemo.juc;

/**
 * @description: 构建一种死锁情况
 * @author: general
 * @version: 1.0
 * @create: 2019-09-08 13:26
 **/
public class DeadLock {
    public synchronized  void demo(){
        System.out.println("demo");
        demo2();
    }
    private void demo2() {
        System.out.println("enter demo2");
        synchronized (this){
        }
    }
    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock::demo).start();
    }
}
