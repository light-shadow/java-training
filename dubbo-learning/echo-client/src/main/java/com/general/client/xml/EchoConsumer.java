package com.general.client.xml;

import com.general.api.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description: 消费者
 * @author: general
 * @version: 1.0
 * @create: 2019-09-22 20:04
 **/
public class EchoConsumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext(new String[]{"spring/echo-consumer.xml"});
        context.start();
        EchoService service = (EchoService)context.getBean("echoService");
        String status = service.echo("Hello world");
        System.out.println("echo result: "+ status);
    }
}
