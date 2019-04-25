package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import admin.Ticket;
import admin.Vip;

public class PrintVipTicket {
	public static void to_EX(Ticket t,List<String> list,Vip v) {
		// 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("小票");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("*****欢迎下次光临*****");
        cell.setCellStyle(style);
        row = sheet.createRow(1);
        cell = row.createCell((short) 0);
        cell.setCellValue(t.getUuid());
        cell.setCellStyle(style);
        row = sheet.createRow(2);
        cell = row.createCell((short) 0);
        cell.setCellValue("收银员："+t.getEmpid());
        cell.setCellStyle(style);
        row = sheet.createRow(3);
        cell = row.createCell((short) 0);
        cell.setCellValue("开票时间："+df.format(t.getDate()));
        cell.setCellStyle(style);
        row = sheet.createRow(4);
        cell = row.createCell((short) 0);
        cell.setCellValue("-------------------------");
        cell.setCellStyle(style);
        row = sheet.createRow(5);
        cell = row.createCell((short) 0);
        cell.setCellValue("菜品编号");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("菜品名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("购买数量");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("菜品单价");
        cell.setCellStyle(style);
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
        	row = sheet.createRow((int) i + 6);
            String sal = list.get(i);
            String[] split = sal.split("-");
            sum+=Double.parseDouble(split[3])*Integer.parseInt(split[2]);
            row.createCell((short) 0).setCellValue(split[0]);
            row.createCell((short) 1).setCellValue(split[1]);
            row.createCell((short) 2).setCellValue(split[2]);
            row.createCell((short) 3).setCellValue(split[3]);
		}
        row = sheet.createRow(5+list.size()+1);
        cell = row.createCell((short) 0);
        cell.setCellValue("-------------------------");
        cell.setCellStyle(style);
        row = sheet.createRow(5+list.size()+2);
        cell = row.createCell((short) 0);
        cell.setCellValue("会员编号：");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue(v.getVipid());
        cell.setCellStyle(style);
        row = sheet.createRow(4+list.size()+3);
        cell = row.createCell((short) 0);
        cell.setCellValue("总价：");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue(sum);
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("应付：");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue(Math.round(v.getVipdiscount()*sum));
        cell.setCellStyle(style);
        row = sheet.createRow(4+list.size()+4);
        cell = row.createCell((short) 0);
        cell.setCellValue("支付：");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue(Math.round(v.getVipdiscount()*sum));
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("账户余额：");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue(Math.round(v.getVipdalance()-v.getVipdiscount()*sum));
        cell.setCellStyle(style);
        CellRangeAddress region = new CellRangeAddress(0,0,0,3);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(1,1,0,3);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(2,2,0,3);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(3,3,0,3);
        sheet.addMergedRegion(region);        
        region = new CellRangeAddress(4,4,0,3);
        sheet.addMergedRegion(region);
        region = new CellRangeAddress(5+list.size()+1,5+list.size()+1,0,3);
        sheet.addMergedRegion(region);
        FileOutputStream fout;
		try {
			fout = new FileOutputStream("D:/小票/"+t.getUuid()+".xls");
			wb.write(fout);
	        fout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
