package com.general.mvcframework.annotation;

import java.lang.annotation.*;

/**
 * Created by general on 2019/6/23.
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SwordAutowired {

    String value() default "";

}
