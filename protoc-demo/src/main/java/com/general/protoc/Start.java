package com.general.protoc;

/**
 * @description: start
 * @author: general
 * @version: 1.0
 * @create: 2019-09-20 09:20
 **/
public class Start {

    public static void main(String[] args) {
        UserProtos.User user=UserProtos.User.newBuilder().
                setAge(300).setName("Mic").build();
        byte[] bytes=user.toByteArray();
        for(byte bt:bytes){
            System.out.print(bt+" ");
        }
    }
}
