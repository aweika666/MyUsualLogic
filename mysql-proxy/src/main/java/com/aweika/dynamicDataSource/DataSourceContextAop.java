package com.aweika.dynamicDataSource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 2020/7/10
 * @description: 数据源切换 切面
 */
@Aspect
@Order(value = 1)
@Component
public class DataSourceContextAop {

    @Around("@annotation(com.aweika.dynamicDataSource.DataSourceSelector)")
    public Object setDynamicDataSource(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean clearFlag = true;
        try {
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            //如果切的是实现接口的controller,则realMethod != targetMethod,切的无实现的controller则 相等
            Method targetMethod = methodSignature.getMethod();
            Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(targetMethod.getName(), targetMethod.getParameterTypes());
            DataSourceSelector dataSourceImport = realMethod.getAnnotation(DataSourceSelector.class);
            //清除标记
            clearFlag = dataSourceImport.clearFlag();
            DataSourceContextHolder.set(dataSourceImport.value().getDataSourceName());
            //log.info("========数据源切换至：{}", dataSourceImport.value().getDataSourceName());
            System.out.println("========数据源切换至：{}" + dataSourceImport.value().getDataSourceName());
            return joinPoint.proceed();
        } finally {
            if (clearFlag) {
                DataSourceContextHolder.clear();
            }
        }
    }

}
