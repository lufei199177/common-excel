package com.common.excel;

import com.common.excel.annotation.Excel;
import com.common.excel.model.ExcelParseModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lufei
 * @date: 2021/2/23 22:16
 * @desc:
 */
public class ExcelUtil {

    public static <T>List<T> importXLSExcel(InputStream is,Class<T> clazz) throws Exception {
        Workbook workbook=new HSSFWorkbook(is);
        return parseExcel(workbook,clazz);
    }

    public static <T>List<T> importXLSXExcel(InputStream is,Class<T> clazz) throws Exception {
        Workbook workbook=new XSSFWorkbook(is);
        return parseExcel(workbook,clazz);
    }

    private static List<ExcelParseModel> parseClass(Class<?> clazz){
        Field[] fields=clazz.getDeclaredFields();
        List<ExcelParseModel> excelParseModels=new ArrayList<>(fields.length);
        for(Field field:fields){
            Excel excel=field.getAnnotation(Excel.class);
            if(excel!=null){
                ExcelParseModel model=new ExcelParseModel();
                model.setExcel(excel);
                model.setField(field);
                excelParseModels.add(model);
            }
        }
        excelParseModels=excelParseModels.stream().sorted(Comparator.comparingInt(a -> a.getExcel().columnIndex())).collect(Collectors.toList());
        return excelParseModels;
    }

    private static <T>List<T> parseExcel(Workbook workbook,Class<T> clazz) throws Exception{
        List<ExcelParseModel> excelParseModels=parseClass(clazz);
        Sheet sheet=workbook.getSheetAt(0);
        List<T> list=new ArrayList<>(sheet.getLastRowNum()+1);

        for(int i=0;i<=sheet.getLastRowNum();i++){
            Row row=sheet.getRow(i);
            T t=clazz.getDeclaredConstructor().newInstance();
            list.add(t);
            for(int j=0;j<excelParseModels.size();j++){
                Cell cell=row.getCell(j);
                String value=cell.getStringCellValue();
                Field field=excelParseModels.get(j).getField();
                Class<?> c=field.getType();

                field.setAccessible(true);
                field.set(t,value);
            }
        }
        return list;
    }
}
