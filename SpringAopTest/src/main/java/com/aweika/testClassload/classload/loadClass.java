package com.aweika.testClassload.classload;

/*
 *
 * @author: Michael
 * @date: 2020/2/9
 * @description:
 *
*/

public class loadClass {
    public static String name = "loadClass";

    public static void method(User user) {
        System.out.println("User的加载器:"+user.getClass().getClassLoader());
        System.out.println("loadClass的静态方法");
    }
}
