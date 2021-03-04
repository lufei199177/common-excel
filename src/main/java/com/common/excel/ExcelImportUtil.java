package com.common.excel;

import com.common.excel.model.ExcelParseModel;
import com.common.excel.util.CommonUtil;
import com.common.excel.util.DataTypeUtil;
import org.apache.poi.ss.usermodel.*;

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
        List<ExcelParseModel> excelParseModels= CommonUtil.parseClass(clazz);
        return parseExcel(workbook,excelParseModels,clazz,1);
    }

    /**
     * @param dataRowIndex 从第几行开始读数据
     */
    public static <T>List<T> importExcel(InputStream is, Class<T> clazz,int headRowIndex,int dataRowIndex) throws Exception {
        Workbook workbook= WorkbookFactory.create(is);
        //校验标题头
        List<ExcelParseModel> excelParseModels= CommonUtil.parseClass(clazz);
        checkHead(workbook,headRowIndex,excelParseModels);
        return parseExcel(workbook,excelParseModels,clazz,dataRowIndex);
    }

    private static void checkHead(Workbook workbook,int headRowIndex,List<ExcelParseModel> excelParseModels){
        Sheet sheet=workbook.getSheetAt(0);
        Row row=sheet.getRow(headRowIndex);
        if(row==null){
            throw new RuntimeException("导入失败，请使用标准模板导入");
        }
        for(int i=0;i<excelParseModels.size();i++){
            Cell cell=row.getCell(i);
            if(cell==null){
                throw new RuntimeException("导入失败，请使用标准模板导入");
            }else{
                String value=cell.getStringCellValue().trim();
                if(!excelParseModels.get(i).getExcel().name().equals(value)){
                    throw new RuntimeException("导入失败，请使用标准模板导入");
                }
            }
        }
    }

    private static <T>List<T> parseExcel(Workbook workbook,List<ExcelParseModel> excelParseModels,Class<T> clazz,int dataRowIndex) throws Exception{

        Sheet sheet=workbook.getSheetAt(0);
        List<T> list=new ArrayList<>(sheet.getLastRowNum()+1);

        StringBuilder sb=new StringBuilder();
        for(int i=dataRowIndex;i<=sheet.getLastRowNum();i++){
            Row row=sheet.getRow(i);
            if(row==null){
                continue;
            }
            sb.delete(0,sb.length());
            T t=clazz.getDeclaredConstructor().newInstance();
            list.add(t);

            for(int j=0;j<excelParseModels.size();j++){
                Cell cell=row.getCell(j);
                ExcelParseModel excelParseModel=excelParseModels.get(j);
                if(cell==null){
                    if(excelParseModel.getExcel().required()){
                        sb.append(excelParseModel.getExcel().name()).append("为空");
                    }
                    continue;
                }

                excelParseModel.setCell(cell);

                Object value= DataTypeUtil.setValue(excelParseModel);
                if(value!=null){
                    Method setMethod=excelParseModel.getSetMethod();
                    setMethod.invoke(t,value);
                }else if(excelParseModel.getExcel().required()){
                    sb.append(excelParseModel.getExcel().name()).append("为空");
                    break;
                }
            }

            try{
                Field rowIndexField= clazz.getSuperclass().getDeclaredField("rowIndex");
                rowIndexField.setAccessible(true);
                rowIndexField.set(t,i+1);

                if(sb.length()>0){
                    Field field= clazz.getSuperclass().getDeclaredField("errorMsg");
                    field.setAccessible(true);
                    field.set(t,sb.toString());
                }
            }catch (Exception e){
                //不做处理
            }
        }
        return list;
    }
}
