package com.aweika.dynamicDataSource;

import java.lang.annotation.*;

/**
 * @author: Michael
 * @date: 2020/7/10
 * @description: 数据源切换注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSelector {
    //默认MASTER数据源
    DynamicDataSourceEnum value() default DynamicDataSourceEnum.MASTER;
    //默认清除线程容器
    boolean clearFlag() default true;
}
