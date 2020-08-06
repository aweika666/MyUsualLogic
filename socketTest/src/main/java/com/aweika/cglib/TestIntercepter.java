package com.aweika.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.omg.PortableInterceptor.Interceptor;

import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 2020/3/11
 * @description:
 */
public class TestIntercepter implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }
}
