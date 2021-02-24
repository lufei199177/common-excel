package com.common.excel;

import com.common.excel.model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author: lufei
 * @date: 2021/2/23 23:43
 * @desc:
 */
public class Test {

    public static void main(String[] args) throws Exception{
        long start=System.currentTimeMillis();
        File file=new File("D:\\test\\test.xlsx");
        List<User> list= ExcelImportUtil.importXLSXExcel(new FileInputStream(file),User.class);

        OutputStream os=new FileOutputStream("D:\\test\\test0.xlsx");
        ExcelExportUtil.exportXLSXExcel(os,list,User.class);
        long end=System.currentTimeMillis();
        System.out.println("耗时："+(end-start));
    }
}
