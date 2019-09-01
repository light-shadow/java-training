package com.general.proxy;

/**
 * @description: 儿子
 * @author: general
 * @version: 1.0
 * @create: 2019-08-06 15:52
 **/
public class Son implements Person{

    @Override
    public void findLove() {
        System.out.println("儿子要求：大长腿");
    }
}
