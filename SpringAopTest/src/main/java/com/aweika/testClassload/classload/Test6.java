package com.aweika.testClassload.classload;

import sun.misc.Launcher;

import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 2020/1/29
 * @description:
 */
public class Test6 {

    public static void main(String[] args) throws Throwable {
        Method findLoadedClass0 = ClassLoader.class.getDeclaredMethod("findLoadedClass0",String.class);
        findLoadedClass0.setAccessible(true);

        MyClassLoader myClassLoader = new MyClassLoader(Launcher.getLauncher().getClassLoader().getParent(),"myClassLoader");

        Class<?> loadClass = myClassLoader.loadClass("com.yscredit.testClassload.classload.loadClass");

        System.out.println(findLoadedClass0.invoke(myClassLoader, "com.yscredit.testClassload.classload.loadClass"));

        System.out.println(findLoadedClass0.invoke(Test6.class.getClassLoader(), "java.lang.Object"));
        System.out.println(findLoadedClass0.invoke(Test6.class.getClassLoader(), "java.lang.String"));

        System.out.println(findLoadedClass0.invoke(myClassLoader, "java.lang.Object"));
    }
}
