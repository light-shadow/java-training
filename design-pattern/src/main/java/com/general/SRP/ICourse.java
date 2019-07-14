package com.general.SRP;

/**
 * @description: 课程接口
 * @author: general
 * @version: 1.0
 * @create: 2019-07-14 11:10
 **/
public interface ICourse {

    String getCourseName();

    byte[] getCourseVideo();

    void studyCourse();

    void studyCourse(String name);

    void refundCourse();
}
