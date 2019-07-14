package com.general.DIP;

/**
 * @description: Tom类
 * @author: general
 * @version: 1.0
 * @create: 2019-07-14 10:36
 **/
public class Tom {

    private ICourse iCourse;

    public Tom() {
    }

    public Tom(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    public void study(ICourse iCourse){
        iCourse.study();
    }

    public void study(){
        this.iCourse.study();
    }

    public void setiCourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }
}
