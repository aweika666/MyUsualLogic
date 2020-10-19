package com.qdsg.data;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: Aweika_Fang
 * @date: 2020/9/17
 * @description:
 */
@Data
public class ComplexHeadData {
    @ExcelProperty({"远程门诊", "字符串标题"})
    private String string;
    @ExcelProperty({"远程门诊", "日期标题"})
    private Date date;
    @ExcelProperty({"远程门诊", "数字标题"})
    private Double doubleData;
}
