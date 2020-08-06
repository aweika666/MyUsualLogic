package com.aweika.testClassload.classload;

import sun.misc.Launcher;

import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 2020/1/29
 * @description:
 */
public class Test7 {
    public static void main(String[] args) throws Throwable  {
        String a = "5";
        Method findLoadedClass0 = ClassLoader.class.getDeclaredMethod("findLoadedClass0",String.class);
        findLoadedClass0.setAccessible(true);
        //自定义类加载器,父类是ext扩展类加载器 跳过了app应用类加载器
        MyClassLoader myClassLoader = new MyClassLoader(/*Launcher.getLauncher().getClassLoader().getParent(),*/"myClassLoader");
        Class sunECClass = myClassLoader.loadClass("sun.security.ec.SunEC");
       /* System.out.println(sunECClass);
        Class<SunEC> sunECClass1 = SunEC.class;
        System.out.println(sunECClass1 == sunECClass); // true*/
        System.out.println(Test7.class.getClassLoader() == myClassLoader.getParent());
        System.out.println("appClassLoader:"+findLoadedClass0.invoke(Test7.class.getClassLoader(), "sun.security.ec.SunEC"));
        System.out.println("myClassLoader:"+findLoadedClass0.invoke(myClassLoader, "sun.security.ec.SunEC"));

        System.out.println(findLoadedClass0.invoke(Test6.class.getClassLoader(), "java.lang.Object"));
        System.out.println(findLoadedClass0.invoke(Test6.class.getClassLoader(), "java.lang.String"));
        System.out.println(myClassLoader.loadClass("sun.misc.Launcher"));
        System.out.println(Launcher.class);
        System.out.println(Launcher.class == myClassLoader.loadClass("sun.misc.Launcher"));

        System.out.println(Object.class);

    }
}
