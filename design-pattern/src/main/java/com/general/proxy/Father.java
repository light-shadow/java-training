package com.general.proxy;

/**
 * @description: 父亲
 * @author: general
 * @version: 1.0
 * @create: 2019-08-06 15:53
 **/
public class Father implements Person{

    private Son son;

    public Father(Son son) {
        this.son = son;
    }

    public void findLove(){
        System.out.println("父母物色对象；");
        this.son.findLove();
        System.out.println("双方ok， 同意交往");
    }

    public static void main(String[] args) {
        Father  father = new Father(new Son());
        father.findLove();
    }
}
