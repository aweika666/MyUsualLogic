package com.aweika.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author: Michael
 * @date: 2020/3/11
 * @description:
 */
public class TestCglib {

    public static void main(String[] args) {
        //使用Enhancer创建代理类
        Enhancer enhancer = new Enhancer();
        //继承被代理类
        enhancer.setSuperclass(MyFather.class);
        //拦截(回调)
        enhancer.setCallback(new TestIntercepter());
        //生成代理对象
        Object o = enhancer.create();
    }

}
