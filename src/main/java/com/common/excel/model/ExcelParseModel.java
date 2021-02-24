package com.common.excel.model;

import com.common.excel.annotation.Excel;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.Field;

/**
 * @author: lufei
 * @date: 2021/2/23 23:31
 * @desc:
 */
public class ExcelParseModel {

    private Excel excel;

    private Field field;

    private Cell cell;

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

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
