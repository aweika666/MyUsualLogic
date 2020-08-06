package com.aweika.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: Michael
 * @date: 2020/3/16
 * @description:
 */
public class TimeUtil {

    /**
     * 根据日期获取年份
     * @param date
     * @return
     */
    public static Integer getDateYear(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        return year;
    }
}
