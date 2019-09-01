package com.general.proxy;

/**
 * @description: 顾客类
 * @author: general
 * @version: 1.0
 * @create: 2019-08-06 16:12
 **/
public class Customer implements Person {
    @Override
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("六块腹肌");
        System.out.println("房产好几处");
    }
}
