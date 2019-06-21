package com.general.netty_demo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @description: 把多个进程的类加入到pipeline中，包括编码、解码、simplechathandler
 * @author: general
 * @version: 1.0
 * @create: 2019-06-21 11:11
 **/
public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("HttpResponseEncoder", new HttpResponseEncoder());
        pipeline.addLast("HttpResponseDecoder", new HttpResponseDecoder());
        pipeline.addLast("chantHandler", new ChatHandler());

        System.out.println("chatClient: [" + ch.remoteAddress() + "] Connect on");
    }
}
