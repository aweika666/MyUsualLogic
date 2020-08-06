package com.aweika.testClassload;

/**
 * @author: Michael
 * @date: 2020/1/27
 * @description:
 */
public class Zi extends Fu {

    public static Integer cc;

    public static final String DD = "DD";

    static {
        System.out.println("Zi的初始化方法begin");
        cc = 55;
        System.out.println("Zi的初始化方法end");
    }

    public static void  ziMethod(){
        System.out.println("Zi的静态方法");
    }
}
