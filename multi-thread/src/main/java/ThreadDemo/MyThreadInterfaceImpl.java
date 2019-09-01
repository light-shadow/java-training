package main.java.ThreadDemo;

/**
 * @description: 实现runnable接口
 * @author: general
 * @version: 1.0
 * @create: 2019-09-01 21:30
 **/
public class MyThreadInterfaceImpl implements Runnable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("my thread"+this.getName());
    }

    public static void main(String[] args) {
        MyThreadInterfaceImpl myThread1 = new MyThreadInterfaceImpl();
        myThread1.setName("myThread1");
        myThread1.run();

        MyThreadInterfaceImpl myThread2 = new MyThreadInterfaceImpl();
        myThread2.setName("myThread2");
        myThread2.run();
    }
}
