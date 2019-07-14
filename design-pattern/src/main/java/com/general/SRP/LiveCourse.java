package com.general.SRP;

/**
 * @description: 直播课
 * @author: general
 * @version: 1.0
 * @create: 2019-07-14 11:12
 **/
public class LiveCourse implements ICourse{
    @Override
    public String getCourseName() {
        return null;
    }

    @Override
    public byte[] getCourseVideo() {
        return new byte[0];
    }

    @Override
    public void studyCourse() {
        System.out.println("");
    }

    @Override
    public void studyCourse(String name) {
        System.out.println("name = [" + name + "]" +"不能反复看" );
    }

    @Override
    public void refundCourse() {

    }
}
