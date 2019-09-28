package com.general.server.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.general.api.EchoService;
import com.general.server.EchoServiceApiImpl;

import java.io.IOException;

/**
 * @description: api方式 不依赖spring
 * @author: general
 * @version: 1.0
 * @create: 2019-09-22 21:01
 **/
public class EchoProvider {

    public static void main(String[] args) throws IOException {
        ServiceConfig<EchoService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("java-echo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        service.setInterface(EchoService.class);
        service.setRef(new EchoServiceApiImpl());
        service.export();
        System.out.println("java-echo-provider is running");
        System.in.read();



    }
}
