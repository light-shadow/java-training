package main.java.ThreadDemo.duty_chain;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 保存处理器
 * @author: general
 * @version: 1.0
 * @create: 2019-09-01 21:47
 **/
public class SaveProcessor extends Thread implements IRequestProcessor {
    private  IRequestProcessor nextRequestProcessor;

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();

    public SaveProcessor() {

    }


    @Override
    public void processRequest(Request request) {
        requests.add(request);
    }

    @Override
    public void run() {
        while(true){
            try {
                Request request = requests.take();
                System.out.println("begin save request:"+request.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
