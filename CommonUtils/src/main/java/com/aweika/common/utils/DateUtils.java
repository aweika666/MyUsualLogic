package com.aweika.common.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils implements Serializable {

    private static final long serialVersionUID = 5L;

    /**2018年放假安排日期*/
    public  static final String[]  HOLIDAY_YEAR = {"2017-12-30","2017-12-31","2018-01-01","2018-02-15","2018-02-16","2018-02-17","2018-02-18","2018-02-19","2018-02-20",
            "2018-02-21","2018-04-05","2018-04-06","2018-04-07","2018-04-29","2018-04-30","2018-05-01","2018-06-16","2018-06-17","2018-06-18","2018-09-22","2018-09-23",
            "2018-09-24","2018-10-01","2018-10-02","2018-10-03","2018-10-04","2018-10-05","2018-10-06","2018-10-07"};

    /**2018年周末上班日期*/
    public  static final String[]  WEEKEND_YEAR = {"2018-02-11","2018-02-24","2018-04-08","2018-04-28","2018-09-29","2018-09-30"};

    /**
     * 将Date 时间转换为指定格式的字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (date == null) {
            return "";
        }
        return sdf.format(date);
    }

    /**
     * 将指定格式的字符串转换为时间
     * @param date
     * @param format
     * @return
     */
    public static Date getFormatDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 将字符串按yyyy-MM-dd HH:mm:ss格式转换为一天后的时间
     * @param date
     * @return
     */
    public static Date getFormatDateAfterOne(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = sdf.parse(date);
            d.setTime(d.getTime() + 24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 将字符串按yyyy-MM-dd HH:mm:ss格式转换为前一天的时间
     * @param date
     * @return
     */
    public static Date getFormatDateBeforeOne(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(date);
            d.setTime(d.getTime() - 24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 将字符串按yyyy-MM-dd格式转换为一天后的时间
     * @param date
     * @return
     */
    public static Date getFormatDateAfterOne(String date, String format) {
        if (format == null || "".equals(format.trim())) {
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = sdf.parse(date);
            d.setTime(d.getTime() + 24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 获得本周一0点时间
     */
    public static Date getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        //如果是周日，直接调用获取的是下周周一时间
        if (1 == cal.get(Calendar.DAY_OF_WEEK)){
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 7);
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }


    /**
     * 获得过去 N 年的日期
     */

    public static String getPastYearDate(Integer years){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();

        //过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -years);
        Date y = c.getTime();
        String year = format.format(y);

        return year;
    }

    //间隔天数 整数
    public static int daysBetween(Date bfdate,Date afdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        bfdate=sdf.parse(sdf.format(bfdate));
        afdate=sdf.parse(sdf.format(afdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(bfdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(afdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    //间隔天数 整数(当天表示一天)
    public static int useDaysBetween(Date bfdate,Date afdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        bfdate=sdf.parse(sdf.format(bfdate));
        afdate=sdf.parse(sdf.format(afdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(bfdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(afdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        int count = Integer.parseInt(String.valueOf(between_days));
        if(count == 0){
            count = 1;
        }
        return count;
    }

    //间隔天数 小数
    public static BigDecimal daysBetweenNum(Date bfdate,Date afdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        bfdate=sdf.parse(sdf.format(bfdate));
        afdate=sdf.parse(sdf.format(afdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(bfdate);
        double time1 = cal.getTimeInMillis();
        cal.setTime(afdate);
        double time2 = cal.getTimeInMillis();
        BigDecimal a = new BigDecimal((time2-time1));
        double between_days =  a.divide(new BigDecimal(1000*3600*24),24,BigDecimal.ROUND_HALF_UP).doubleValue();

        return new BigDecimal(between_days);
    }

    /**
     * 判断是否是工作日
     * @return false:不是,true:是
     */
    public static Boolean isWorkDay(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String todayDate = sf.format(date);
            if (Arrays.asList(HOLIDAY_YEAR).contains(todayDate)){     //判断今天的日期是否是放假日期
                return false;
            }

            if (Arrays.asList(WEEKEND_YEAR).contains(todayDate)){     //判断今天的日期是否周末上班日期
                return true;
            }

            Date currentDate = sf.parse(todayDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentDate);
            if (cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){    //判断今天的日期是否是周末休息日期
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            System.out.println("时间转换错误："+e);
        }
        return true;
    }

    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }

        return weekDays[w];

    }
    
    /**
     * 解析format指定的格式的日期
     */
    public static Date parseDate(String date, String format) {
        return parseDate(date, new SimpleDateFormat(format));
    }


    /**
     * 解析format指定的格式的日期
     */
    public static Date parseDate(String date, SimpleDateFormat format) {
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException("Date parse error: " + date
                    + " , expected format is " + format.toPattern(), ex);
        }
    }

}
