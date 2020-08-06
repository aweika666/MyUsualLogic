package com.aweika2.package2;

import com.aweika2.package1.Fu;
import com.aweika2.package1.Zi;

import java.lang.reflect.Method;

/**
 * @author: Michael
 * @date: 2020/4/6
 * @description:
 */
public class Test extends Fu{

    public static void main(String[] args) throws Exception {
        /*
        1.父类的protected成员是包内可见的；
        2.若子类与父类不在同一包中，那么在子类中，该子类型引用可以访问父类的protected方法。其他的类型的引用都不能访问父类的protected方法。
        */
        Test a = new Test();
        a.method();//Fu的方法
        //
        Zi b = new Zi();
        Method method = Fu.class.getDeclaredMethod("method");
        method.setAccessible(true);
        method.invoke(b, null);  //Zi的方法
        // b.method(); error

        //
        Test c = new Test();
        Method method2 = Fu.class.getDeclaredMethod("defaultMethod");
        method2.setAccessible(true);
        method2.invoke(c, null);  //defaultMethod的方法
    }

    public void methodTest(){
        this.method();
        Fu a = new Fu();
        //a.method();
        /*Test test = new Test();
        test.methodPrivate();*/
    }

    private void methodPrivate(){
        System.out.println("private");
    }

    @Override
    public void method() {
        super.method();
    }
}
