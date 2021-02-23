package com.common.excel.model;

/**
 * @author: lufei
 * @date: 2021/2/24 0:05
 * @desc:
 */
public enum YesOrNoEnum {

    YES("是"),
    NO("否");

    YesOrNoEnum(String desc){
        this.desc=desc;
    }

    private final String desc;

    public String getDesc() {
        return desc;
    }
}
