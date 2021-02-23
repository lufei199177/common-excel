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

    int columnIndex();

    //String propName();

    String headName();

    boolean required();
}
