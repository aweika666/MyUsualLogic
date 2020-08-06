package com.aweika.common.utils;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.springboot.cloud.pjqyadmin.config.OssConfig;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Fangqizhe
 * @Date: 2019/8/29 12:30 AM
 * @Description: excel上传 将数据转成List集合
 */
public class ExcelReadUtils {
    /**
     * 功能描述: 读取数据
     * @param :wb beginRow(表头所在的行数,传入null 则表头在第一行)
     * @return :java.util.List<java.util.List<java.lang.String>>
     */
    public static List<List<String>> excelToList(Workbook wb,Integer beginRow) {
        //总行数
        Integer totalRows = 0;
        //总列数
        Integer totalCells = 0;
        List<List<String>> dataLst = new ArrayList<>();
        // 得到 sheet1
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的总行数
        totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的总列数
        if (totalRows >= 1 && sheet.getRow(0) != null) {
            //beginRow 从表头行获取总列数
            totalCells = sheet.getRow(beginRow == null? 0 : beginRow).getPhysicalNumberOfCells();
        }else {
            //不符合则抛出异常
            throw new RuntimeException("excel没有数据");
        }
        /*if (beginRow != null)
            beginRow = beginRow + 1;*/
        // 循环Excel的行
        for (int rowNum = 0; rowNum < totalRows; rowNum++) {
            Row row = null;
            //若beginRow是null 则第一行是excel表头字段 从第二行数据行开始,否则用beginRow
            if (beginRow != null)
                row = sheet.getRow(beginRow + rowNum + 1);
            else
                row =  sheet.getRow(rowNum+1);

            if (row == null) {
                continue;
            }
            List<String> rowLst = new ArrayList<>();
            // 循环Excel的列
            for (int c = 0; c <totalCells ; c++) {
                Cell cell = row.getCell(c);
                String cellValue = "";
                /*if (cell != null){
                    if (isCellDateFormatted(cell)){ // 时间格式
                        String dateString = dateString(cell);
                        cellValue = dateString;
                    }else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                        //DecimalFormat df = new DecimalFormat("0");
                        BigDecimal bigDecimal = new BigDecimal(cell.toString());
                        //cellValue = df.format(cell.getNumericCellValue());
                        cellValue = bigDecimal.toPlainString();
                    }else if (cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String value = cell.getStringCellValue();
                        cellValue = value;
                    }else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
                        cellValue = cell.getBooleanCellValue() + "";
                    }else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA){
                        cellValue = cell.getCellFormula() + "";
                    }else if (cell.getCellType() == Cell.CELL_TYPE_BLANK){
                        cellValue = "";
                    }else if (cell.getCellType() == Cell.CELL_TYPE_ERROR){
                        cellValue = "非法字符";
                    }else{
                        cellValue = "未知类型";
                    }
                }*/

                /*if (null != cell) {
                    // 判断数据的类型
                    switch (cell.getCellType()) {
                        // 数字 默认按照导入数据的精度(但是如果数据库是decimal(10,2))
                        case Cell.CELL_TYPE_NUMERIC:
                            //DecimalFormat df = new DecimalFormat("0");
                            //BigDecimal bigDecimal = new BigDecimal(cell.toString());
                            //cellValue = df.format(cell.getNumericCellValue());
                            //cellValue = bigDecimal.toString();
                            //break;
                            if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                                SimpleDateFormat sdf = null;
                                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                                    sdf = new SimpleDateFormat("HH:mm");
                                } else {// 日期
                                    sdf = new SimpleDateFormat("yyyy年MM月dd日");
                                }
                                Date date = cell.getDateCellValue();
                                cellValue = sdf.format(date);
                            } else if (cell.getCellStyle().getDataFormat() == 58) {
                                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                                double value = cell.getNumericCellValue();
                                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
                                cellValue = sdf.format(date);
                            } else {
                                BigDecimal bigDecimal = new BigDecimal(cell.toString());
                                cellValue = bigDecimal.toPlainString();//避免出现科学计数法
                            }
                            break;


                        case Cell.CELL_TYPE_STRING: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;

                        case Cell.CELL_TYPE_BOOLEAN: // Boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;

                        case Cell.CELL_TYPE_FORMULA: // 公式
                            cellValue = cell.getCellFormula() + "";
                            break;

                        case Cell.CELL_TYPE_BLANK: // 空值
                            cellValue = "";
                            break;

                        case HSSFCell.CELL_TYPE_ERROR: // 故障
                            cellValue = "非法字符";
                            break;

                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }
*/
                if (cell != null) {
                    int cellType=cell.getCellType();
                    switch (cellType) {
                        case Cell.CELL_TYPE_NUMERIC: // 数字
                            short format = cell.getCellStyle().getDataFormat();
                            if (DateUtil.isCellDateFormatted(cell)) {
                                SimpleDateFormat sdf = null;
                                //System.out.println("cell.getCellStyle().getDataFormat()="+cell.getCellStyle().getDataFormat());
                                if (format == 20 || format == 32) {
                                    sdf = new SimpleDateFormat("HH:mm");
                                } else if (format == 14 || format == 31 || format == 57 || format == 58) {
                                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    double value = cell.getNumericCellValue();
                                    Date date = DateUtil
                                            .getJavaDate(value);
                                    cellValue = sdf.format(date);
                                }else {// 日期
                                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                }
                                try {
                                    cellValue = sdf.format(cell.getDateCellValue());// 日期
                                } catch (Exception e) {
                                    try {
                                        throw new Exception("exception on get date data !".concat(e.toString()));
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                    }
                                }finally{
                                    sdf = null;
                                }
                            }  else {
                                BigDecimal bd = new BigDecimal(Double.toString(cell.getNumericCellValue())); //转换成string 避免数值异常
                                cellValue = bd.toPlainString();//防止获取到科学计数值
                            }
                            break;
                        case Cell.CELL_TYPE_STRING: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_BOOLEAN: // Boolean
                            cellValue = cell.getBooleanCellValue()+"";;
                            break;
                        case Cell.CELL_TYPE_FORMULA: // 公式
                            cellValue = cell.getCellFormula();
                            break;
                        case Cell.CELL_TYPE_BLANK: // 空值
                            cellValue = "";
                            break;
                        case Cell.CELL_TYPE_ERROR: // 故障
                            cellValue = "ERROR VALUE";
                            break;
                        default:
                            cellValue = "UNKNOW VALUE";
                            break;
                    }
                }

                rowLst.add(cellValue);
            }
            // 保存第r行的第c列
            dataLst.add(rowLst);
        }
        return dataLst;
    }


    private  static boolean isCellDateFormatted(Cell cell) {
        if (cell == null) return false;
        boolean isDate = false;
        double d =cell.getNumericCellValue();
        if ( DateUtil.isValidExcelDate(d) ){
            CellStyle style =cell.getCellStyle();
            if(style==null) return false;
            int i = style.getDataFormat();
            String f =style.getDataFormatString();
            isDate = DateUtil.isADateFormat(i,f);
        }
        return isDate;
    }

    /**
     * 从日期型cell单元格中得到字符串格式化日期
     * @title:
     * @author xinyaoli
     * @description:
     * @date
     * @return 格式化好的日期字符串
     * @throws Exception
     */
    private static String dateString(Cell cell){
        String datestr = "";
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
            Date date =cell.getDateCellValue();
            DateFormat formater = new SimpleDateFormat("yyyy年MM月dd日");
            datestr =formater.format(date);
//             System.out.println(datestr);
        }
        return datestr;
    }


    /**
     *   根据url获取文件输入流
     * @param url
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getStream(String url) throws FileNotFoundException {
        url = url.replace(OssConfig.ENTFILE_ACCESSHTTPS, "");
        //从oss读取文件
        String fileTitle = url.substring(url.lastIndexOf("-") + 1, url.length());
        OSSClient ossClient = new OSSClient(OssConfig.ENTFILE_ENDPOINT,OssConfig.ENTFILE_ACCESSKEYID,OssConfig.ENTFILE_ACCESSKEYSECRET);
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(OssConfig.ENTFILE_BUCKETNAME, url), new File(OssConfig.UPLOAD_PATH + fileTitle));
        //临时文件下载
        File file = new File(OssConfig.UPLOAD_PATH + fileTitle);
        FileInputStream stream = new FileInputStream(file);
        return stream;
    }
}
