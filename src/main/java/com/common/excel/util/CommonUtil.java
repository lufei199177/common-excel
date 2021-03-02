package com.common.excel.util;

import com.common.excel.annotation.Excel;
import com.common.excel.model.ExcelParseModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lufei
 * @date: 2021/2/24 23:11
 * @desc:
 */
public class CommonUtil {

    public static List<ExcelParseModel> parseClass(Class<?> clazz) throws NoSuchMethodException {
        Field[] fields=clazz.getDeclaredFields();
        List<ExcelParseModel> excelParseModels=new ArrayList<>(fields.length);
        for(Field field:fields){
            Excel excel=field.getAnnotation(Excel.class);
            if(excel!=null){
                ExcelParseModel model=new ExcelParseModel();
                model.setExcel(excel);
                model.setField(field);
                String methodName="set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
                Method method=clazz.getDeclaredMethod(methodName,field.getType());
                model.setSetMethod(method);
                excelParseModels.add(model);
            }
        }
        excelParseModels=excelParseModels.stream().sorted(Comparator.comparingInt(a -> a.getExcel().seq())).collect(Collectors.toList());
        return excelParseModels;
    }
}
