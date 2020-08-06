package com.aweika2.package1;

/**
 * @author: Michael
 * @date: 2020/4/6
 * @description:
 */
public class Fu {
    private String name ;

    public String getName() {
        return name;
    }

    public Fu setName(String name) {
        Zi zi = new Zi();
       // zi.name = name; //compile error
       // zi.privateMethod(); //compile error

        Fu zi1 = new Zi(); //父类型(本类中本类型)引用,子类对象
        zi1.name = name; //compile ok
        zi1.privateMethod(); //private成员只能通过本类中本类型的引用去访问

        this.name = name;
        return this;
    }

    protected void method(){
        System.out.println("Fu的方法");
    }

    void defaultMethod(){
        System.out.println("defaultMethod");
    }

    private void privateMethod(){
        System.out.println("privateMethod");
    }

}
