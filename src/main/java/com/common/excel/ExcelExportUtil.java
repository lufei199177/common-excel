package com.common.excel;

import com.common.excel.model.ExcelParseModel;
import com.common.excel.util.CommonUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Integer,CellStyle> cellStyleMap=new HashMap<>();
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
                    switch (map.get(field.getType().getName())){
                        case 0:
                            cell.setCellValue(String.valueOf(value));
                            break;
                        case 1:
                            cell.setCellValue(Double.parseDouble(String.valueOf(value)));
                            break;
                        case 2:
                            cell.setCellValue((boolean)value);
                            break;
                        case 3:
                            CellStyle cellStyle = cellStyleMap.get(j);
                            if(cellStyle==null){
                                cellStyle=workbook.createCellStyle();
                                DataFormat format= workbook.createDataFormat();
                                cellStyle.setDataFormat(format.getFormat(model.getExcel().dateFormat()));
                                cellStyleMap.put(j,cellStyle);
                            }
                            cell.setCellStyle(cellStyle);
                            cell.setCellValue((Date)value);
                            break;
                    }
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

    private static final Map<String, Integer> map=new HashMap<>();

    static {
        map.put("java.lang.String", 0);
        map.put("char", 0);
        map.put("java.lang.Short", 1);
        map.put("short", 1);
        map.put("java.lang.Integer", 1);
        map.put("int", 1);
        map.put("java.lang.Long", 1);
        map.put("long", 1);
        map.put("java.lang.Float", 1);
        map.put("float", 1);
        map.put("java.lang.Double", 1);
        map.put("double", 1);
        map.put("java.math.BigDecimal", 1);
        map.put("java.lang.Boolean", 2);
        map.put("boolean", 2);
        map.put("java.util.Date", 3);
    }
}
