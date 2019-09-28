package main.java.ThreadDemo.juc.conditionDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: app
 * @author: general
 * @version: 1.0
 * @create: 2019-09-11 12:32
 **/
public class App {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(new ConditionWait(lock, condition)).start();
        new Thread(new ConditionSignal(lock, condition)).start();
    }
}
