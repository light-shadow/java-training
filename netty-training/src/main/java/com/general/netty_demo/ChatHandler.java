package com.general.netty_demo;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.util.concurrent.GlobalEventExecutor;


public class ChatHandler extends SimpleChannelInboundHandler {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * @description: 读取来自client发送的消息，并把它重定向到其他client的channel上
     * @param: [ctx, object] 
     * @return: void 
     * @author: general 
     * @date: 2019-06-21 
    */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object object) throws Exception {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer("Hello netty".getBytes())
        );
        response.headers().set("Content-Type", "text/plain");
        response.headers().set("Content-Length", response.content().readableBytes());
        response.headers().set("connection", HttpHeaderValues.KEEP_ALIVE);
        ctx.writeAndFlush(response);
    }
    /** 
     * @description: 无论何时，服务端接收到一个新的client的连接时，client的channel被储存在Channel Group list中，并且会通知
     * list中的其他client
     * @param: [ctx]
     * @return: void 
     * @author: general 
     * @date: 2019-06-21 10:54
    */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for(Channel channel:channels){
            channel.writeAndFlush("[Server ] - " + incoming.remoteAddress() + " Add \n");
        }
        channels.add(ctx.channel());
    }
    /**
     * @description: 无论何时，client断开与server端的连接时，client的channel在Channel Group list中被删除，并且会通知
     * list中的其他client
     * @param: [ctx]
     * @return: void
     * @author: general
     * @date: 2019-06-21 11:09
    */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for(Channel channel: channels){
            channel.writeAndFlush("[Server ] - " + incoming.remoteAddress() + " Remove \n");
        }
        channels.remove(ctx.channel());
    }
    
    /** 
     * @description: 当一个session建立的时候 
     * @param: [ctx] 
     * @return: void 
     * @author: general 
     * @date: 2019-06-21 11:10
    */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client [" + incoming.remoteAddress() + "] already online");
    }
    
    /** 
     * @description: 在一个session快结束的时候
     * @param: [ctx] 
     * @return: void 
     * @author: general 
     * @date: 2019-06-21 11:10
    */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client " + incoming.remoteAddress() + "already offline");
    }

    /**
     * @description: 出现异常
     * @param: [ctx, cause]
     * @return: void
     * @author: general
     * @date: 2019-06-21 11:11
    */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client " + incoming.remoteAddress() + "exception");
        cause.printStackTrace();
        ctx.close();
    }
}
