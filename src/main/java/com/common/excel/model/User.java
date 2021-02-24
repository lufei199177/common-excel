package com.common.excel.model;

import com.common.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: lufei
 * @date: 2021/2/23 23:40
 * @desc:
 */
public class User {

    @Excel(columnIndex = 0,headName = "id",required = true)
    private String id;

    @Excel(columnIndex = 1,headName = "姓名",required = true)
    private String name;

    @Excel(columnIndex = 2,headName = "身高",required = true)
    private Short height;

    @Excel(columnIndex = 3,headName = "年龄",required = true)
    private Integer age;

    @Excel(columnIndex = 4,headName = "长度",required = true)
    private Long length;

    @Excel(columnIndex = 5,headName = "税率",required = true)
    private Float tax;

    @Excel(columnIndex = 6,headName = "金额",required = true)
    private Double amount;

    @Excel(columnIndex = 7,headName = "支付",required = true)
    private BigDecimal pay;

    @Excel(columnIndex = 8,headName = "flag",required = true)
    private Boolean flag;

    @Excel(columnIndex = 9,headName = "日期",required = true)
    private Date date;

    @Excel(columnIndex = 10,headName = "级别")
    private char level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getHeight() {
        return height;
    }

    public void setHeight(Short height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public char getLevel() {
        return level;
    }

    public void setLevel(char level) {
        this.level = level;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", age=" + age +
                ", length=" + length +
                ", tax=" + tax +
                ", amount=" + amount +
                ", pay=" + pay +
                ", flag=" + flag +
                ", date=" + date +
                ", level=" + level +
                '}';
    }
}
