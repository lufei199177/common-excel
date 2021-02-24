package com.common.excel;

import com.common.excel.model.ExcelParseModel;
import com.common.excel.util.CommonUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author: lufei
 * @date: 2021/2/24 23:06
 * @desc:
 */
public class ExcelExportUtil {

    public static <T>void exportXLSExcel(OutputStream outputStream, List<T> list,Class<T> clazz) throws Exception {
        Workbook workbook=new HSSFWorkbook();
        createExcel(workbook,list,clazz);
        workbook.write(outputStream);
    }

    public static <T>void exportXLSXExcel(OutputStream outputStream, List<T> list,Class<T> clazz) throws Exception {
        Workbook workbook=new XSSFWorkbook();
        createExcel(workbook,list,clazz);
        workbook.write(outputStream);
    }

    public static <T>void createExcel(Workbook workbook, List<T> list,Class<T> clazz) throws IllegalAccessException {
        List<ExcelParseModel> excelParseModels= CommonUtil.parseClass(clazz);
        Sheet sheet=workbook.createSheet();
        //创建标题头
        createHead(sheet,excelParseModels);
        //赋值
        for(int i=0;i<list.size();i++){
            T t=list.get(i);
            Row row=sheet.createRow(i+1);
            for(int j=0;j<excelParseModels.size();j++){
                ExcelParseModel model=excelParseModels.get(j);
                Field field=model.getField();
                field.setAccessible(true);
                Object value=field.get(t);
                if(value!=null){
                    Cell cell=row.createCell(j);
                    cell.setCellValue(String.valueOf(value));
                }
            }
        }
    }

    private static void createHead(Sheet sheet,List<ExcelParseModel> excelParseModels){
        Row row=sheet.createRow(0);
        for(int i=0;i<excelParseModels.size();i++){
            ExcelParseModel excelParseModel=excelParseModels.get(i);
            Cell cell=row.createCell(i);
            cell.setCellValue(excelParseModel.getExcel().name());
            sheet.setColumnWidth(i,excelParseModel.getExcel().width()*30);
        }
    }
}
