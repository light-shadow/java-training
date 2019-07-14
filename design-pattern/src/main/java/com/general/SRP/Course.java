package com.general.SRP;

/**
 * @description: 没有使用设计模式之前
 * @author: general
 * @version: 1.0
 * @create: 2019-07-14 11:07
 **/
public class Course {

    public void study(String courseName){
        if("直播课".equals(courseName)){
            System.out.println("courseName = [" + courseName + "]" + "不能快进");
        }
        else{
            System.out.println("courseName = [" + courseName + "]" + "可以反复回看");
        }
    }

    public static void main(String[] args) {
        Course course = new Course();
        course.study("直播课");
        course.study("录播课");
    }
}
