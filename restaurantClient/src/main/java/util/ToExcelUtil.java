package util;


import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */

public class ToExcelUtil {

    public static void toEx(List<String> list) throws Exception
    {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("销量表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("菜品名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("菜品销量");
        cell.setCellStyle(style);
        


        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，


        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            String sal = list.get(i);
            String[] split = sal.split("-");
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(split[0]);
            row.createCell((short) 1).setCellValue(split[1]);
            

        }
        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream("E:/销量排行.xls");
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
