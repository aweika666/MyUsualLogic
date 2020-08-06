package com.aweika.testClassload.classload;

import sun.security.ec.SunEC;

/**
 * @author: Michael
 * @date: 2020/1/29
 * @description:
 */
public class Test9 {
    public static void testMethod(){
        Class<SunEC> sunECClass = SunEC.class;
        System.out.println(sunECClass);
    }

    public static void test(){
        Class<SunEC> sunECClass = SunEC.class;
    }
}
