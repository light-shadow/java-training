package com.general.DIP;

/**
 * @description: ICourse实现类
 * @author: general
 * @version: 1.0
 * @create: 2019-07-14 10:36
 **/
public class AICourse  implements ICourse{

    @Override
    public void study() {
        System.out.println("Tom is studying AI");
    }
}
