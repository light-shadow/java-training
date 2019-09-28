package main.java.ThreadDemo.juc.conditionDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description: signal
 * @author: general
 * @version: 1.0
 * @create: 2019-09-11 12:30
 **/
public class ConditionSignal implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionSignal(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin-condtion signal demo");
        try {
            lock.lock();
            condition.signal();
            System.out.println("end-condtion signal demo");
        }finally {
            lock.unlock();
        }
    }
}
