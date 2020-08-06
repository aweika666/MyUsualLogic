package com.aweika.testClassload.classload;

import sun.misc.Launcher;

import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 2020/1/29
 * @description:
 */
public class Test8 {
    public static void main(String[] args) throws Throwable   {
        Method findLoadedClass0 = ClassLoader.class.getDeclaredMethod("findLoadedClass0",String.class);
        findLoadedClass0.setAccessible(true);
        //自定义类加载器,父类是ext扩展类加载器 跳过了app应用类加载器
        MyClassLoader myClassLoader = new MyClassLoader(Launcher.getLauncher().getClassLoader().getParent(),"myClassLoader");
        Class test9Class = myClassLoader.loadClass("com.yscredit.testClassload.classload.Test9");
        test9Class.getMethod("testMethod").invoke(null);
        System.out.println("myClassLoader:"+findLoadedClass0.invoke(myClassLoader, "sun.security.ec.SunEC"));
    }

}
