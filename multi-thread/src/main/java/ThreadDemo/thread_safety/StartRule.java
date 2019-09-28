package main.java.ThreadDemo.thread_safety;

/**
 * @description: 验证thread的start原则
 * @author: general
 * @version: 1.0
 * @create: 2019-09-07 22:12
 **/
public class StartRule {

    static int x = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            System.out.println(x); // 输出结果为10
        });
        x = 10;
        thread.start();
    }
}
