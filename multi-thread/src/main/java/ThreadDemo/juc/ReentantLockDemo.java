package main.java.ThreadDemo.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 重入锁demo
 * @author: general
 * @version: 1.0
 * @create: 2019-09-08 16:17
 **/
public class ReentantLockDemo {

    static Lock lock = new ReentrantLock();

    public void demo(){
        lock.lock(); // 获得一把锁
        // read
        lock.unlock();//释放一把锁
    }
}
