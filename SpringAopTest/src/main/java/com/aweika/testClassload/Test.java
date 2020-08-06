package com.aweika.testClassload;

import sun.misc.Launcher;

/**
 * @author: Michael
 * @date: 2020/1/26
 * @description:
 */
public class Test{
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        System.out.println(cl);


        ClassLoader appClassLoader = Launcher.getLauncher().getClassLoader();
        System.out.println(appClassLoader);
        ClassLoader Launcher$ExtClassLoader = appClassLoader.getParent();
        System.out.println(Launcher$ExtClassLoader);
        System.out.println(Launcher$ExtClassLoader.getClass().getClassLoader());
        //System.out.println(Launcher.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());


        Class<?> aClass = Class.forName("com.yscredit.service.impl.TestServiceImpl");
    }
}
