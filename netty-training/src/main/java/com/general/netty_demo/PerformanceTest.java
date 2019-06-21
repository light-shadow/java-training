package com.general.netty_demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

/**
 * @description: 性能测试
 * @author: general
 * @version: 1.0
 * @create: 2019-06-21 13:48
 **/
public class PerformanceTest {

    public static void main(String[] args) {
        final byte[] content = new byte[1024];
        int loop = 1800000000;
        long startTime = System.currentTimeMillis();
        ByteBuf poolBuffer = null;
        for(int i=0;i<loop;i++){
            poolBuffer = PooledByteBufAllocator.DEFAULT.directBuffer(1024);
            poolBuffer.writeBytes(content);
            poolBuffer.release();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("内存池分配缓冲区耗时： " + (endTime - startTime));

        long startTime2 = System.currentTimeMillis();
        ByteBuf buffer = null;
        for(int i=0;i<loop;i++){
            buffer = Unpooled.directBuffer(1024);
            buffer.writeBytes(content);
            buffer.release();
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("非内存池分配缓冲区耗时： "+ (endTime2 - startTime2));
    }
}
