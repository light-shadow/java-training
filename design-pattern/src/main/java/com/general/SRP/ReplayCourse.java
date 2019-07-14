package com.general.SRP;

/**
 * @description: 录播课
 * @author: general
 * @version: 1.0
 * @create: 2019-07-14 11:14
 **/
public class ReplayCourse implements ICourse {
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

    }

    @Override
    public void studyCourse(String name) {
        System.out.println("name = [" + name + "]" + "可以反复看" );
    }

    @Override
    public void refundCourse() {

    }
}
