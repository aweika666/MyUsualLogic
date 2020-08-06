package com.demo.starter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Michael
 * @date: 2020/2/6
 * @description:
 */
@ConfigurationProperties(prefix = "demo.msg")
public class MsgProperties {

    public MsgProperties() {
        System.out.println("MsgProperties构造器调用了");
    }

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 地址
     */
    private String address;


    public String getName() {
        return name;
    }

    public MsgProperties setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public MsgProperties setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public MsgProperties setAddress(String address) {
        this.address = address;
        return this;
    }
}
