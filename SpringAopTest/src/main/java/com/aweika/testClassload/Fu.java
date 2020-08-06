package com.aweika.testClassload;

import com.alibaba.fastjson.JSON;

/**
 * @author: Michael
 * @date: 2020/1/26
 * @description:
 */
public class Fu {

    private String name;
    private Integer age;
    public static Integer aa;

    public static final String BB = "BB";

    static {
        System.out.println("Fu的初始化方法begin");
        aa = 55;
        System.out.println("Fu的初始化方法end");
    }

   /* public String getName() {
        return name;
    }

    public Fu setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Fu setAge(Integer age) {
        this.age = age;
        return this;
    }*/

    public static void main(String[] args) {
        Fu fu = new Fu();
        fu.age=15; fu.name="aa";
        String s = JSON.toJSONString(fu);
        System.out.println(s);

       /* User user = new User("fangqizhe",16);
        //Haha haha = new Haha("杭州龙湖一期", true);
        //Object[] objects = {user, haha};
        System.out.println(JSON.toJSONString(user));*/
    }


}
