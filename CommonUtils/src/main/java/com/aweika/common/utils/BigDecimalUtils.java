package com.aweika.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author: Fangqizhe
 * @Date: 2019/8/25 10:19 PM
 * @Description: BigDecimal工具类
 */
public class BigDecimalUtils {
    //小于等于 传参必须保证非空
    public static boolean lessThanOrEqual(BigDecimal compare1, BigDecimal compare2) {
        return compare1.compareTo(compare2) == -1 || compare1.compareTo(compare2) == 0;
    }
    //大于等于 传参必须保证非空
    public static boolean moreThanOrEqual(BigDecimal compare1, BigDecimal compare2) {
        return compare1.compareTo(compare2) == 1 || compare1.compareTo(compare2) == 0;
    }
    //小于 传参必须保证非空
    public static boolean lessThan(BigDecimal compare1, BigDecimal compare2) {
        return compare1.compareTo(compare2) == -1;
    }
    //得到非空结果,如果是null 返回BigDecimal.ZERO
    public static BigDecimal getNotNullResult(BigDecimal b) {
        return b == null ? BigDecimal.ZERO : b;
    }

    //除法,保留2位小数,四舍五入 ,报错返回BigDecimal.ZERO
    public static BigDecimal getDivideResult(BigDecimal a, BigDecimal b) {
        //避免报错
        try {
            return a.divide(b, 2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO.setScale(2);
        }
    }
    //相加
    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        if(a == null){
            a = BigDecimal.ZERO;
        }
        if(b == null){
            b = BigDecimal.ZERO;
        }
        return a.add(b);
    }

    //得到非"null"的结果
    public static String getNotNullString(BigDecimal bigDecimal){
        String value = String.valueOf(bigDecimal);
        if("null".equals(value)){
            return "";
        }
        return value;
    }
}
