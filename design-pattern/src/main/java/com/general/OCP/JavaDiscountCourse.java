package com.general.OCP;

/**
 * @description: java课程的优惠类
 * @author: general
 * @version: 1.0
 * @create: 2019-07-13 10:55
 **/
public class JavaDiscountCourse extends JavaCourse {

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    /**
     * @description: 返回原课程价格
     * @param: []
     * @return: java.lang.Double
     * @author: general
     * @date: 2019-07-13 10:57
    */
    public Double getOriginPrice(){
        return super.getPrice();
    }

    /**
     * @description: 返回打折完之后的课程价格
     * @param: []
     * @return: java.lang.Double
     * @author: general
     * @date: 2019-07-13 10:57
    */
    public Double getPrice(){
        return super.getPrice() * 0.61;
    }
}
