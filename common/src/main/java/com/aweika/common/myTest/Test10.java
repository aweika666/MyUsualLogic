package com.aweika.common.myTest;

import sun.misc.Launcher;

/**
 * @author: Michael
 * @date: 2020/2/7
 * @description:
 */
public class Test10 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader(Launcher.getLauncher().getClassLoader().getParent(),"myClassLoader");

        Class<?> loadClass = myClassLoader.loadClass("com.yscredit.aaa.classload.loadClass");
        System.out.println(loadClass.getName());
        /*Class<loadClass> loadClassClass = loadClass.class;
        System.out.println(loadClassClass.getName());*/
    }
}
