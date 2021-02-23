package com.common.excel.model;

import com.common.excel.annotation.Excel;

import java.lang.reflect.Field;

/**
 * @author: lufei
 * @date: 2021/2/23 23:31
 * @desc:
 */
public class ExcelParseModel {

    private Excel excel;

    private Field field;

    public Excel getExcel() {
        return excel;
    }

    public void setExcel(Excel excel) {
        this.excel = excel;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
