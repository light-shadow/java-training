package com.general.server;

import com.alibaba.dubbo.rpc.RpcContext;
import com.general.api.EchoService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: api使用的实现类
 * @author: general
 * @version: 1.0
 * @create: 2019-09-22 21:04
 **/
public class EchoServiceApiImpl implements EchoService {



    @Override
    public String echo(String messag) {
        String now = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("[" + now + "] " + messag + ", request from consumer: "
                +RpcContext.getContext().getRemoteAddress());
        return messag;
    }
}
