package com.general.client.annotation;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.general.client.refer.EchoConsumer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 注解消费者
 * @author: general
 * @version: 1.0
 * @create: 2019-09-22 20:37
 **/
public class AnnotationConsumer {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new
                AnnotationConfigApplicationContext(ConsumerProviderConfig.class);
        context.start();
        EchoConsumer consumer = context.getBean(EchoConsumer.class);
        String msg = consumer.echo("Hello World");
        System.out.println("result: " + msg);
    }


    @Configuration
    @EnableDubbo(scanBasePackages = "com.general.client")
    @ComponentScan(value = {"com.general.client"})
    static class ConsumerProviderConfig{
        @Bean
        public ApplicationConfig applicationConfig(){
            ApplicationConfig config = new ApplicationConfig();
            config.setName("echo-annotation-consumer");
            return config;
        }

        @Bean
        public ConsumerConfig consumerConfig(){
            return new ConsumerConfig();
        }

        @Bean
        public RegistryConfig registryConfig(){
            RegistryConfig config = new RegistryConfig();
            config.setProtocol("zookeeper");
            config.setAddress("localhost");
            config.setPort(2181);
            return config;
        }
    }

}
