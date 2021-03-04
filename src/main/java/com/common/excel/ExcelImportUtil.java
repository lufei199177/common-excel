package com.common.excel;

import com.common.excel.model.ExcelParseModel;
import com.common.excel.util.CommonUtil;
import com.common.excel.util.DataTypeUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lufei
 * @date: 2021/2/23 22:16
 * @desc:
 */
public class ExcelImportUtil {

    public static <T>List<T> importExcel(InputStream is, Class<T> clazz) throws Exception {
        Workbook workbook= WorkbookFactory.create(is);
        return parseExcel(workbook,clazz);
    }

    private static <T>List<T> parseExcel(Workbook workbook,Class<T> clazz) throws Exception{
        List<ExcelParseModel> excelParseModels= CommonUtil.parseClass(clazz);
        Sheet sheet=workbook.getSheetAt(0);
        List<T> list=new ArrayList<>(sheet.getLastRowNum()+1);

        for(int i=1;i<=sheet.getLastRowNum();i++){
            Row row=sheet.getRow(i);
            if(row==null){
                continue;
            }
            T t=clazz.getDeclaredConstructor().newInstance();
            list.add(t);
            for(int j=0;j<excelParseModels.size();j++){
                Cell cell=row.getCell(j);
                if(cell==null){
                    continue;
                }
                ExcelParseModel excelParseModel=excelParseModels.get(j);
                excelParseModel.setCell(cell);

                Object value= DataTypeUtil.setValue(excelParseModel);
                if(value!=null){
                    /*field.setAccessible(true);
                    field.set(t,value);*/
                    Method setMethod=excelParseModel.getSetMethod();
                    setMethod.invoke(t,value);
                }else if(excelParseModel.getExcel().required()){
                    throw new Exception("第"+i+"行"+excelParseModel.getExcel().name()+"为空");
                }
            }
        }
        return list;
    }
}
