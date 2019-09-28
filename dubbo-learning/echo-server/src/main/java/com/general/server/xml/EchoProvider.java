package com.general.server.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @description: 容器启动类
 * @author: general
 * @version: 1.0
 * @create: 2019-09-22 20:16
 **/
public class EchoProvider {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/echo-provider.xml"}
        );
        ((ClassPathXmlApplicationContext) context).start();
        System.in.read();
    }
}
