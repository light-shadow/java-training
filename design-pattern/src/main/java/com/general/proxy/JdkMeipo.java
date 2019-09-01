package com.general.proxy;


import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 媒婆代理类
 * @author: general
 * @version: 1.0
 * @create: 2019-08-06 16:08
 **/
public class JdkMeipo implements InvocationHandler {

    private Object target;

    public Object getInstance(Object target) throws Exception{
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(),
                clazz.getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object obj = method.invoke(this.target, args);
        after();
        return obj;
    }

    private void after() {
        System.out.println("如果合适的话，就开始办事！");
    }

    private void before() {
        System.out.println("我是媒婆，我已经拿到你的需求了");
        System.out.println("开始物色对象");
    }

    public static void main(String[] args) {
        try {
            Person obj = (Person)new JdkMeipo().getInstance(new Customer());
            obj.findLove();

            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",
                    new Class[]{Person.class});
            FileOutputStream os = new FileOutputStream("E://$Proxy0.class");
            os.write(bytes);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
