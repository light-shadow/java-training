package com.general.server.annotation;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @description: 注解方式提供服务
 * @author: general
 * @version: 1.0
 * @create: 2019-09-22 20:23
 **/
public class AnnotationProvider {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ProviderConfiguration.class);
        context.start();
        System.in.read();
    }

    @Configuration
    @EnableDubbo(scanBasePackages = "com.general.server")
    static class ProviderConfiguration{

        @Bean
        public ProviderConfig providerConfig(){
            return new ProviderConfig();
        }

        @Bean
        public ApplicationConfig applicationConfig(){
            ApplicationConfig config = new ApplicationConfig();
            config.setName("echo-annotation-provider");
            return config;
        }

        @Bean
        public RegistryConfig registryConfig(){
            RegistryConfig config = new RegistryConfig();
            config.setProtocol("zookeeper");
            config.setAddress("localhost");
            config.setPort(2181);
            return config;
        }

        @Bean
        public ProtocolConfig protocolConfig(){
            ProtocolConfig config = new ProtocolConfig();
            config.setName("dubbo");
            config.setPort(20880);
            return config;
        }
    }
}
