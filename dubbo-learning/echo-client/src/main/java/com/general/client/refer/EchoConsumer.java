package com.general.client.refer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.general.api.EchoService;
import org.springframework.stereotype.Component;

/**
 * @description: 注解形式
 * @author: general
 * @version: 1.0
 * @create: 2019-09-22 20:36
 **/
@Component
public class EchoConsumer {

    @Reference
    private EchoService echoService;

    public String echo(String name){
        return echoService.echo(name);
    }

}
