package main.java.ThreadDemo.thread_safety;

/**
 * @description: 线程安全性
 * @author: general
 * @version: 1.0
 * @create: 2019-09-01 22:26
 **/
public class AtmoticDemo {

    static int count = 0;

    static void incre(){
        try{
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<1000;i++){
            new Thread(()->incre()).start();
        }
        Thread.sleep(5000);
        System.out.println("结果是:"+count);
    }
}
