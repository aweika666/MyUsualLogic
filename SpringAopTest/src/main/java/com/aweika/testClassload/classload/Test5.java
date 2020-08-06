package com.aweika.testClassload.classload;

import sun.misc.Launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 2020/1/28
 * @description:
 */
public class Test5 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Object o1 = new Object();
        Method findLoadedClass0 = ClassLoader.class.getDeclaredMethod("findLoadedClass0",String.class);
        findLoadedClass0.setAccessible(true);



        System.out.println("Test5的类加载器:"+Test5.class.getClassLoader());
        ClassLoader extClassLoader = Launcher.getLauncher().getClassLoader().getParent();
        MyClassLoader myClassLoader = new MyClassLoader(extClassLoader,"MyClassLoader");

        System.out.println(findLoadedClass0.invoke(myClassLoader, "java.lang.Object"));


        Class<?> loadClassClass = myClassLoader.loadClass("com.yscredit.testClassload.classload.loadClass");
        System.out.println(findLoadedClass0.invoke(myClassLoader, "java.lang.Object"));
        //findLoadedClass0 , 如果该类加载器是这个类的 定义类加载器(define loader) 则肯定会返回该类的class对象.
        //当类加载去加载一个类的时候,如果jvm将这个类加载器判定为这个类的 初始化类加载器(initiating loader)则findLoadedClass0也会返回该类的class对象.
        //具体得看jvm怎么判定这个加载器是否是这个类的初始化加载器,比较复杂,没必要研究. 在jvm判断这个类是否加载的时候,会从当前类的定义类加载器的命名空间开始查找
        //命名空间: 每个类加载器都有自己的命名空间，命名空间由该加载器及所有父加载器所加载的类组成。
        /*
        不同类加载器的命名空间关系
            同一个命名空间内的类是相互可见的
            子加载器的命名空间包含所有父加载器的命名空间。因此由子加载器加载的类能看见父加载器加载的类。例如系统类加载器加载的类能看见根类加载器加载的类。
            由父加载器加载的类不能看见子加载器加载的类。
            如果两个类加载器之间没有直接或间接的父子关系，那么他们各自加载的类相互不可见。
        */
        System.out.println(findLoadedClass0.invoke(myClassLoader, "java.lang.String"));
        Class<?> aClass = myClassLoader.loadClass("java.lang.String"); //主动调用,object不会将这个加载器设为初始化加载器
        System.out.println(findLoadedClass0.invoke(myClassLoader, "java.lang.String"));
        System.out.println(findLoadedClass0.invoke(Test5.class.getClassLoader(), "java.lang.Object"));
        /*System.out.println(loadClassClass);
        System.out.println("loadClass的类加载器"+loadClassClass.getClassLoader());
        System.out.println("--------------");
        System.out.println("--------------");
        System.out.println("--------------");*/

        /*Class<?> aClass = Launcher.getLauncher().getClassLoader().loadClass("com.yscredit.testClassload.classload.loadClass");
        System.out.println(aClass);


        System.out.println(aClass == loadClassClass);*/

      /*  loadClass.method();
        System.out.println(loadClass.class.getClassLoader());*/


        Object o = loadClassClass.newInstance();
        System.out.println(o);
    }
}
