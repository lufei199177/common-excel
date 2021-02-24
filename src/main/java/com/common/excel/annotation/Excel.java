package com.common.excel.annotation;

import java.lang.annotation.*;

/**
 * @author: lufei
 * @date: 2021/2/23 22:17
 * @desc:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {

    int seq();

    int width() default 120;

    String name();

    boolean required() default false;
}
