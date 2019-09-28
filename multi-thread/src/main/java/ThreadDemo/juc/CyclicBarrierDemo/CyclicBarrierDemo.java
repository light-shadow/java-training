package main.java.ThreadDemo.juc.CyclicBarrierDemo;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CyclicBarrier;

/**
 * @description: CyclicBarrier example
 * @author: general
 * @version: 1.0
 * @create: 2019-09-17 21:12
 **/
public class CyclicBarrierDemo extends Thread{

    @Override
    public void run() {
        System.out.println("开始进行数据分析");
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,
                new CyclicBarrierDemo());
        new Thread(new DataImportThread(cyclicBarrier, "file1")).start();
        new Thread(new DataImportThread(cyclicBarrier, "file2")).start();
        new Thread(new DataImportThread(cyclicBarrier, "file3")).start();
    }
}
