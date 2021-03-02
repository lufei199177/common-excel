package com.common.excel;

import com.common.excel.model.User;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
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
        List<User> list= ExcelImportUtil.importExcel(new FileInputStream(file),User.class);

        System.out.println(list);

        /*OutputStream os=new FileOutputStream("D:\\test\\test0.xlsx");
        ExcelExportUtil.exportXLSXExcel(os,list,User.class);
        long end=System.currentTimeMillis();
        System.out.println("耗时："+(end-start));*/
        /*InputStream is=new FileInputStream("C:\\Users\\ADMIN\\Downloads\\小区导入模板-0207.1.xlsx");
        Workbook workbook= WorkbookFactory.create(is);
        Sheet sheet=workbook.getSheetAt(0);

        System.out.println(sheet.getLastRowNum());
        System.out.println(workbook);
        System.out.println(sheet);
        System.out.println(sheet.getColumnStyle(0).toString());*/
    }
}
