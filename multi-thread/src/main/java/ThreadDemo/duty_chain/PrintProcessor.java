package main.java.ThreadDemo.duty_chain;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description: 打印处理器
 * @author: general
 * @version: 1.0
 * @create: 2019-09-01 21:42
 **/
public class PrintProcessor extends Thread implements IRequestProcessor{

    private  IRequestProcessor nextRequestProcessor;

    LinkedBlockingQueue<Request> requests = new LinkedBlockingQueue<Request>();

    public PrintProcessor() {
    }

    public PrintProcessor(IRequestProcessor nextProcessor) {
        this.nextRequestProcessor = nextProcessor;
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
                System.out.println("print request:"+request.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
