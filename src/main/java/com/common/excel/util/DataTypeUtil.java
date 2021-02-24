package com.common.excel.util;

import com.common.excel.model.ExcelParseModel;
import org.apache.poi.ss.usermodel.CellType;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author: lufei
 * @date: 2021/2/24 9:21
 * @desc:
 */
public class DataTypeUtil {

    private static final Map<String, Function<ExcelParseModel,Object>> map=new HashMap<>();

    static {
        map.put("java.lang.String", DataTypeUtil::handleString);
        map.put("char", DataTypeUtil::handleChar);
        map.put("java.lang.Short", DataTypeUtil::handleShort);
        map.put("short", DataTypeUtil::handleShort);
        map.put("java.lang.Integer", DataTypeUtil::handleInteger);
        map.put("java.lang.Long", DataTypeUtil::handleLong);
        map.put("java.lang.Float", DataTypeUtil::handleFloat);
        map.put("java.lang.Double", DataTypeUtil::handleDouble);
        map.put("java.math.BigDecimal", DataTypeUtil::handleBigDecimal);
        map.put("java.lang.Boolean", DataTypeUtil::handleBoolean);
        map.put("java.util.Date", DataTypeUtil::handleDate);
    }

    public static Object setValue(ExcelParseModel model) throws IllegalAccessException {
        Field field=model.getField();
        Function<ExcelParseModel,Object> function=map.get(field.getType().getName());
        return function.apply(model);
    }

    private static Object handleString(ExcelParseModel model){
        CellType cellType=model.getCell().getCellTypeEnum();

        String value=null;
        if(cellType==CellType.STRING){
            value=model.getCell().getStringCellValue();
        }else if(cellType==CellType.NUMERIC){
            value=String.valueOf(model.getCell().getNumericCellValue());
        }

        return value;
    }

    private static Object handleChar(ExcelParseModel model){
        CellType cellType=model.getCell().getCellTypeEnum();

        char value = 0;
        if(cellType==CellType.STRING){
            String v=model.getCell().getStringCellValue();
            value=v.charAt(0);
        }else if(cellType==CellType.NUMERIC){
            int v=(int)model.getCell().getNumericCellValue();
            value= (char) v;
        }

        return value;
    }

    private static Object handleShort(ExcelParseModel model){
        CellType cellType=model.getCell().getCellTypeEnum();

        Short value=null;
        if(cellType==CellType.NUMERIC){
            double v=model.getCell().getNumericCellValue();
            value=(short) v;
        }/*else if(cellType==CellType.STRING){
            value=Float.parseFloat(model.getCell().getStringCellValue());
        }*/

        return value;
    }

    private static Object handleInteger(ExcelParseModel model){
        CellType cellType=model.getCell().getCellTypeEnum();

        Integer value=null;
        if(cellType==CellType.NUMERIC){
            double v=model.getCell().getNumericCellValue();
            value=(int) v;
        }/*else if(cellType==CellType.STRING){
            value=Float.parseFloat(model.getCell().getStringCellValue());
        }*/

        return value;
    }

    private static Object handleLong(ExcelParseModel model){
        CellType cellType=model.getCell().getCellTypeEnum();

        Long value=null;
        if(cellType==CellType.NUMERIC){
            double v=model.getCell().getNumericCellValue();
            value=(long) v;
        }/*else if(cellType==CellType.STRING){
            value=Float.parseFloat(model.getCell().getStringCellValue());
        }*/

        return value;
    }

    private static Object handleFloat(ExcelParseModel model){
        CellType cellType=model.getCell().getCellTypeEnum();
        
        Float value=null;
        if(cellType==CellType.NUMERIC){
            double v=model.getCell().getNumericCellValue();
            value=(float) v;
        }/*else if(cellType==CellType.STRING){
            value=Float.parseFloat(model.getCell().getStringCellValue());
        }*/
        
        return value;
    }

    private static Object handleDouble(ExcelParseModel model){
        CellType cellType=model.getCell().getCellTypeEnum();

        Double value=null;
        if(cellType==CellType.NUMERIC){
            value=model.getCell().getNumericCellValue();
        }/*else if(cellType==CellType.STRING){
            value=Double.parseDouble(model.getCell().getStringCellValue());
        }*/

        return value;
    }

    private static Object handleBigDecimal(ExcelParseModel model){
        CellType cellType=model.getCell().getCellTypeEnum();

        BigDecimal value=null;
        if(cellType==CellType.NUMERIC){
            double v=model.getCell().getNumericCellValue();
            value=new BigDecimal(v);
        }/*else if(cellType==CellType.STRING){
            value=new BigDecimal(model.getCell().getStringCellValue());
        }*/

        return value;
    }

    private static Object handleBoolean(ExcelParseModel model){
        CellType cellType=model.getCell().getCellTypeEnum();
        
        Boolean value=null;
        if(cellType==CellType.BOOLEAN){
            value=model.getCell().getBooleanCellValue();
        }
        return value;
    }

    private static Object handleDate(ExcelParseModel model){
        CellType cellType=model.getCell().getCellTypeEnum();

        Date date=null;
        if(cellType==CellType.NUMERIC){
            date=model.getCell().getDateCellValue();
        }

        return date;
    }
}
