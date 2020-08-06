package com.aweika.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 2019/12/22
 * @description:  unsafe 不需要调用类的构造器就能创建对象(成员字段全部为null)
 */
public class UnsafeHelper {
    private UnsafeHelper() {
    }

    private static final Object unsafe;

    private static final Method allocateInstance;

    static {

        final Class<?> unsafeClass;

        final Field theUnsafeField;

        try {
            unsafeClass = Class.forName("sun.misc.Unsafe");
        } catch (ClassNotFoundException e) {
            throw new UnsupportedOperationException("can't find sun.misc.Unsafe. " + e.getMessage(), e);
        }

        try {
            theUnsafeField = unsafeClass.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            throw new UnsupportedOperationException("can't find the field theUnsafe in sun.misc.Unsafe." + e.getMessage(), e);
        }
        theUnsafeField.setAccessible(true);

        try {
            unsafe = theUnsafeField.get(null);
        } catch (IllegalAccessException e) {
            throw new UnsupportedOperationException("get Unsafe instance failed: " + e.getMessage(), e);
        }

        try {
            allocateInstance = unsafeClass.getMethod("allocateInstance", Class.class);
        } catch (NoSuchMethodException e) {
            throw new UnsupportedOperationException("can't find the method allocateInstance in sun.misc.Unsafe : " + e.getMessage(),
                    e);
        }

    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return (T) allocateInstance.invoke(unsafe, clazz);
        } catch (Exception e) {
            throw new UnsupportedOperationException("create instance for " + clazz + " failed. " + e.getMessage(), e);
        }
    }

}
