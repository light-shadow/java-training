package main.java.ThreadDemo.juc.conditionDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description: conditon demo
 * @author: general
 * @version: 1.0
 * @create: 2019-09-11 12:26
 **/
public class ConditionWait implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin-condtion wait demo");
        try {
            lock.lock();
            condition.await();
            System.out.println("end-condtion wait demo");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
