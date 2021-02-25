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

    @Excel(seq = 0,name = "id",width = 80,required = true)
    private String id;

    @Excel(seq = 1,name = "姓名",width = 100,required = true)
    private String name;

    @Excel(seq = 2,name = "身高",width = 80)
    private Short height;

    @Excel(seq = 3,name = "年龄",width = 80)
    private Integer age;

    @Excel(seq = 4,name = "长度",width = 80)
    private Long length;

    @Excel(seq = 5,name = "税率",width = 80)
    private Float tax;

    @Excel(seq = 6,name = "金额",width = 80)
    private Double amount;

    @Excel(seq = 7,name = "支付",width = 80)
    private BigDecimal pay;

    @Excel(seq = 8,name = "flag",width = 80)
    private Boolean flag;

    @Excel(seq = 9,name = "日期",width = 120,dateFormat = "yyyy-MM-dd")
    private Date date;

    @Excel(seq = 10,name = "级别",width = 60)
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
