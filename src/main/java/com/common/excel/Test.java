package com.common.excel;

import com.common.excel.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * @author: lufei
 * @date: 2021/2/23 23:43
 * @desc:
 */
public class Test {

    public static void main(String[] args) throws Exception{
        File file=new File("D:\\test\\test.xlsx");
        List<User> list=ExcelUtil.importXLSXExcel(new FileInputStream(file),User.class);
        System.out.println(list);
    }
}
