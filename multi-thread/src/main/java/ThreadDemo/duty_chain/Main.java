package main.java.ThreadDemo.duty_chain;

/**
 * @description:
 * @author: general
 * @version: 1.0
 * @create: 2019-09-01 21:48
 **/
public class Main {

    PrintProcessor printProcessor;

    protected Main() {
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();
        printProcessor= new PrintProcessor(saveProcessor);
        printProcessor.start();

    }

    private void todoTest(Request request){
        printProcessor.processRequest(request);
    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setName("general's test");
        new Main().todoTest(request);
    }
}
