package com.aweika.common.utils;

/**
 * @Author: Fangqizhe
 * @Date: 2019/9/6 3:40 PM
 * @Description:
 */


import com.github.pagehelper.util.StringUtil;

/**
 *  正则校验-工具类
 */
public class RegularUtil {

    /** 中文数字 */
    private static final String CHINESE_NUMBER = "[一二三四五六七八九〇○o零十]";
    /** 中文大写数字 */
    private static final String CHINESE_NUMBER_UPPER = "[壹贰叁肆伍陆柒捌玖零〇○o十拾]";
    /** 年 */
    private static final String YEAR = "(((19|20)\\d{2})年||((19|20)\\d{2})|((一九|二〇|二○|二o|二零)"+CHINESE_NUMBER+CHINESE_NUMBER+")年|((一九|二〇|二○|二o|二零)"+CHINESE_NUMBER_UPPER+CHINESE_NUMBER_UPPER+")年)";
    /** 月 */
    private static final String MONTH = "(.{1,2}月|-\\d{1,2}|/\\d{1,2}|／\\d{1,2}|"+CHINESE_NUMBER+CHINESE_NUMBER+"月|(一十)"+CHINESE_NUMBER+"月|"+CHINESE_NUMBER_UPPER+CHINESE_NUMBER_UPPER+"月|(壹拾)"+CHINESE_NUMBER_UPPER+"月)";
    /** 日 */
    private static final String DAY = "(.{1,2}日|-\\d{1,2}|/\\d{1,2}|／\\d{1,2}|"+CHINESE_NUMBER+CHINESE_NUMBER+"日|"+CHINESE_NUMBER_UPPER+CHINESE_NUMBER_UPPER+"日|((一十)|(二十)|(三十))"+CHINESE_NUMBER+"日|((壹拾)|(贰拾)|(叁拾))"+CHINESE_NUMBER_UPPER+"日)";


    /** 组织机构代码 */
    public static final String ORG_CODE = "[0-9A-Za-z]{9}";
    /** 注册号 */
    public static final String REG_NO = "[0-9]{10,15}|[0-9]{6}NA[0-9]{6}X|[0-9]{6}NB[0-9]{6}X";
    /** 手机号 */
    public static final String MOBILE = "(?:0|86|\\+86)?1[3456789]\\d{9}";
    /** 移动手机号 */
    public static final String MOBILE_CMCC = "(?:0|86|\\+86)?1((3[456789])|(47)|(5[012789])|(8[2378]))\\d{8}";
    /** 联通手机号 */
    public static final String MOBILE_CUCC = "(?:0|86|\\+86)?1((3[012])|(45)|(5[56])|(8[56]))\\d{8}";
    /** email */
    public static final String EMAIL = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";
    /** 统一社会信用代码 */
    @Deprecated
    public static final String REG_CREDIT_CODE = "9[1|2|3]([A-Za-z0-9]{16})";
    /** 统一社会信用代码 */
    public static final String CREDIT_CODE = "9[1|2|3]([A-Za-z0-9]{16})";
    /** 个体统一社会信用代码 */
    public static final String GT_CREDIT_CODE = "92([A-Za-z0-9]{16})";

    /** 有数ID */
    public static final String ENT_ID = "[q|g]([A-Za-z0-9_]{31,32})";
    /** 医院ID */
    public static final String HOSPITAL_ID = "[h]([A-Za-z0-9_]{31,32})";
    /** 年月日 */
    public static final String YEAR_MONTH_DAY = YEAR + MONTH + DAY;
    /** 开庭公告案号提取 */
    public static final String COURT_NOTICE_NO = "[〔（(]\\d\\s*\\d\\s*\\d\\s*\\d*\\s*[)）〕][\\s\\S]*?[号]";
    /** 一代身份证 */
    public static final String ID_CARD_FIRST = "[1-9]\\d{5}\\d{2}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}";
    /** 二代身份证 */
    public static final String ID_CARD_SECOND = "[1-9]\\d{5}[12]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}(\\d|X|x)";
    /** 中文汉字 */
    public static final String CHINESE_CHARACTERS = "[\u4e00-\u9fff]+";
    /** 中文汉字 */
    public static final String CHINESE_CHARACTERS_BRACKETS = "[\u4e00-\u9fffa-zA-Z0-9]+";
    /** 中文汉字加下划线 */
    public static final String CHINESE_CHARACTERS_LINE = "[\u4e00-\u9fffa-zA-Z0-9]+";
    /** URL */
    public static final String URL = "(https://|http://)?([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    /** 密码（英文数字下划线） */
    public final static String PASSWORD = "\\w{6,18}";
    /** 纯数字 */
    public final static String NUMBER = "\\d+";
    /** 英文字母 */
    public final static String ENGLISH = "[a-zA-Z]+";
    /** 数字加英文 */
    public final static String NUMBER_ENGLISH = "[0-9A-Za-z]+";
    /** 科学计数法 */
    public final static String SCIENTIFIC_NOTATION = "\\d{1}(\\.\\d{1,})?E\\d+";
    /** 数字英文和中文 */
    public final static String NUMBER_ENGLISH_CHINESE = "[0-9a-zA-Z\u4e00-\u9fff]+";
    /** 数字英文和中文下划线 */
    public final static String NUMBER_ENGLISH_CHINESE_UNDERLINE = "[0-9a-zA-Z_|-\u4e00-\u9fff]+";
    /** 整数 */
    public final static String IS_INTEGER = "[+-]?\\d+";
    /** double值 */
//    ((-?\\d+.?\\d*)[Ee]{1}(-?\\d+)) 科学计数法
//    [+-]?\\d+(\\.\\d+)? 普通duble
    public final static String IS_DOUBLE = "([+-]?\\d+(\\.\\d+)?)|(([-+]?\\d+\\.?\\d*)[Ee]{1}([-+]?\\d+))";
    /** 各种圆点正则表达式 */
    public static final String SPOTS = "((&#8226;)|(\u2022)|·|⋅|∙|・|•|●)";
    /** 全角字符 */
    public static final String SBC_CHAR = "^[０-９ａ-ｚＡ-Ｚ]*$";
    /** MD5 */
    public static final String MD5 = "^([a-fA-F0-9]{32})$";



    /**
     *  验证正则表达式。
     *  @param arg 参数
     *  @param regex 规则
     *  @return 是否匹配
     */
    public static boolean validate(String arg, String regex) {
        return StringUtil.isNotEmpty(arg) && arg.matches(regex);
    }

    public static void main(String[] args) {
        System.err.println("中文");
        System.err.println(validate("中文", CHINESE_CHARACTERS_BRACKETS));
        System.out.println(validate("18668058350",MOBILE));
    }
}
