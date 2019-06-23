package com.general.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * Created by general on 2019/6/23.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SwordRequestMapping {


    String value() default "";
}