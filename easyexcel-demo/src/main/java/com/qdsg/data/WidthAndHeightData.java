package com.qdsg.data;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * @author: Aweika_Fang
 * @date: 2020/9/17
 * @description:
 */
@Data
@ContentRowHeight(20) //内容的高度
@HeadRowHeight(40)    //头部的高度
@ColumnWidth(25)      //列宽
public class WidthAndHeightData {
    @ExcelProperty({"远程门诊","字符串标题"})
    private String string;

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = {"远程门诊","日期标题"})
    private Date date;
    /**
     * 宽度为50
     */
    //@ColumnWidth(50)
    @ExcelProperty({"远程门诊","数字标题"})
    private Double doubleData;

}
