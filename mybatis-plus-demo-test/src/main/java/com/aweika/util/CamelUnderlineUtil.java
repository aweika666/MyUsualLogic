package com.aweika.util;



import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Aweika_Fang
 * @date: 2020/9/8
 * @description: 使用Java反射机制将Bean对象转换成Map(驼峰命名方式 —下划线命名方式)
 */
public class CamelUnderlineUtil {

    private static final char UNDERLINE ='_';
    /**
     * 驼峰命名转换成下划线方式名称，eg：cfConfigRecord > cf_config_record
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = param.length();
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线方式名称转换成驼峰命名，eg：cf_config_record > cfConfigRecord
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param){
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = param.length();
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c==UNDERLINE) {
                if(++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Bean对象转Map方法<br/><br/>
     *
     *
     * @param obj
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Map<String, String> convertObjectToMap(Object obj, Class clazz) throws Exception {
        Map<String, String> dstMap = new HashMap<String, String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if ("serialVersionUID".equals(field.getName()) || field.getName().contains("$")) {
                continue;
            }

            String dstName = CamelUnderlineUtil.camelToUnderline(field.getName());
            PropertyDescriptor pd;
            pd = new PropertyDescriptor(field.getName(), clazz);
            Method method = pd.getReadMethod();
            Object dstObject = method.invoke(obj);
            if (dstObject instanceof Date) {

               // dstObject = DateUtil.dateToString((Date)dstObject);
            }
            if (dstObject instanceof ArrayList) {
                dstObject = "";
            }
            dstMap.put(dstName, dstObject == null ? "" : dstObject.toString());
        }
        return dstMap;
    }
}
