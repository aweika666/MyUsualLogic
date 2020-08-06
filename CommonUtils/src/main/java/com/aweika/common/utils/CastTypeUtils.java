package com.aweika.common.utils;

import com.alibaba.fastjson.util.TypeUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author: Michael
 * @date: 2020/7/2
 * @description: 转换类型
 */
public class CastTypeUtils {

    public static Object  checkTypeAndCast(Object obj , Field target){
        if (obj == null || target == null)
            return  null;
        //变量的类型
        Class<?> type = target.getType();

        if (type == Integer.class ){
            return TypeUtils.castToInt(obj);
        }else if (type == Long.class){
            return TypeUtils.castToLong(obj);
        }else if (type == BigDecimal.class){
            return TypeUtils.castToBigDecimal(obj);
        }else if (type == String.class){
            return TypeUtils.castToString(obj);
        }

        return obj;
    }

    public static Object  checkTypeAndCast(Object obj , Object target){
        if (obj == null || target == null)
            return  null;

        if (target instanceof Integer){
            return TypeUtils.castToInt(obj);
        }else if (target instanceof Long){
            return TypeUtils.castToLong(obj);
        }else if (target instanceof BigDecimal){
            return TypeUtils.castToBigDecimal(obj);
        }else if (target instanceof String){
            return TypeUtils.castToString(obj);
        }

        return obj;
    }
}
