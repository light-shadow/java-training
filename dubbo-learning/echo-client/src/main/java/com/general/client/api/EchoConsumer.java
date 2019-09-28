package com.general.client.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.general.api.EchoService;

/**
 * @description: api方式消费者
 * @author: general
 * @version: 1.0
 * @create: 2019-09-22 21:06
 **/
public class EchoConsumer {

    public static void main(String[] args) {
        ReferenceConfig<EchoService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("java-echo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setInterface(EchoService.class);
        EchoService service = reference.get();
        String msg = service.echo("Hello World");
        System.out.println(msg);
    }
}
