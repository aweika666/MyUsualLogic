package com.aweika.cglib;

/**
 * @author: Michael
 * @date: 2020/3/11
 * @description:
 */
public class MyFather {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public MyFather setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public MyFather setAge(Integer age) {
        this.age = age;
        return this;
    }

    public void method(){
        System.out.println("i am MyFather");
    }

    public static void main(String[] args) {
        double a ;
        int flag = 0;
        if (flag == 1){
            throw new RuntimeException();
        }else {
            a =5;
        }
        System.out.println(a);

    }
}
