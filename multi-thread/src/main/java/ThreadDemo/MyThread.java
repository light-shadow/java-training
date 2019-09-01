package main.java.ThreadDemo;

/**
 * @description: 线程创建
 * @author: general
 * @version: 1.0
 * @create: 2019-09-01 21:23
 **/
public class MyThread extends Thread{


    @Override
    public void run() {
        System.out.println("my thread"+this.getName());
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        myThread1.setName("myThread1");
        myThread1.start();

        MyThread myThread2 = new MyThread();
        myThread2.setName("myThread2");
        myThread2.start();
    }
}
