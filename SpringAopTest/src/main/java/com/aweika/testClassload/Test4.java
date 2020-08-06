package com.aweika.testClassload;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 2020/1/27
 * @description:
 */
public class Test4 extends ClassLoader{
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Test4 flag = new Test4();
        Class<?> loadedClass = flag.findLoadedClass("java.lang.String");
        Class<?> aClass = flag.loadClass("com.aweika.testClassload.Test4");
        System.out.println(loadedClass);
        System.out.println("-----------------");
        System.out.println("-----------------");

        Method findLoadedClass0 = ClassLoader.class.getDeclaredMethod("findLoadedClass0",String.class);
        findLoadedClass0.setAccessible(true);

        /*
        系统类加载器
         */
        ClassLoader appClassLoader = flag.getParent();
        System.out.println(appClassLoader);
        Object oa = findLoadedClass0.invoke(appClassLoader, "java.lang.String");
        System.out.println(oa);
        System.out.println("-----------------");
        System.out.println("-----------------");

        /*
        exct类加载器
         */
        ClassLoader extClassLoader = appClassLoader.getParent();
        System.out.println(extClassLoader);
        Object oe = findLoadedClass0.invoke(extClassLoader, "java.lang.String");
        Class<?> stringClass = extClassLoader.loadClass("java.lang.String");
        System.out.println(oe);
        System.out.println("-----------------");
        System.out.println("-----------------");

        /*
        根加载器
         */
        ClassLoader bootClassLoader = extClassLoader.getParent();
        System.out.println(bootClassLoader);
        Object ob = findLoadedClass0.invoke(bootClassLoader, "java.lang.String");
        System.out.println(ob);
        System.out.println("-----------------");
        System.out.println("-----------------");
    }
}
