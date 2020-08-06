package com.aweika.common.utils;

import org.springframework.context.ApplicationContext;

/**
 * @author: Michael
 * @date: 2020/1/7
 * @description:
 */
public class ApplicationContextUtil {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        applicationContext = applicationContext;
    }
}
