package com.aweika.testClassload.classload;

import com.aweika.testClassload.classload.MyClassLoader;
import sun.misc.Launcher;

import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 2020/2/7
 * @description:
 */
public class Test10 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        MyClassLoader myClassLoader = new MyClassLoader(Launcher.getLauncher().getClassLoader().getParent(),"myClassLoader");

        Class<?> aloadClass = myClassLoader.loadClass("com.aweika.testClassload.classload.loadClass");
        System.out.println(aloadClass.getClassLoader());
        User user = new User();
        System.out.println("User的加载器:"+user.getClass().getClassLoader());

        //Class<?> userCLass = myClassLoader.loadClass("com.com.aweika.testClassload.classload.User");
        Method method = aloadClass.getMethod("method",user.getClass());



        /*Class appLoadClass = loadClass.class;
        System.out.println(appLoadClass.getClassLoader());*/
        //  System.out.println(loadClass.getName());
        /*Class<loadClass> loadClassClass = loadClass.class;
        System.out.println(loadClassClass.getName());*/
    }
}
