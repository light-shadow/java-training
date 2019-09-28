package main.java.ThreadDemo.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 读写锁使用场景
 * @author: general
 * @version: 1.0
 * @create: 2019-09-08 15:46
 **/
public class WirteReadLockDemo {

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Map<String, Object> map = new HashMap<String, Object>();

    static Lock read = readWriteLock.readLock();
    static Lock write = readWriteLock.writeLock();

    static {
        map.put("name", "steve");
        map.put("age", "18");
        map.put("gender", "male");
    }

    public static final Object get(String key){
        System.out.println("begin read key： "+key);
        read.lock();
        try {
            return map.get(key);
        }finally {
            read.unlock();
        }
    }

    public static final Object put(String key, Object val){
        System.out.println("begin putting key:"+key+", val:"+val);
        write.lock();
        try {
            return map.put(key, val);
        }finally {
            write.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println(get("name"));
            System.out.println(get("age"));
            System.out.println(get("gender"));
        }, "getThread");

        Thread t2 = new Thread(()->{
            put("name", "general");
            put("age", "26");
            put("gender", "male1");
        }, "puttingThread");


    }

}
