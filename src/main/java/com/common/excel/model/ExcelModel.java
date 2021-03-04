package com.common.excel.model;

import java.io.Serializable;

/**
 * @author: lufei
 * @date: 2021/3/4 18:57
 * @desc:
 */
public class ExcelModel implements Serializable {

    private int rowIndex;

    private String errorMsg;

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
