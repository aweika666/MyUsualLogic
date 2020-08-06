package com.demo.starter.template;

import com.demo.starter.properties.MsgProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author: Michael
 * @date: 2020/2/6
 * @description:
 */


public class MsgTemplate {
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

    public MsgTemplate setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public MsgTemplate setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public MsgTemplate setAddress(String address) {
        this.address = address;
        return this;
    }


    public String getMessage() {
        return "名字:" + this.name + ",年龄:" + this.age + ",地址:" + this.address;
    }
}
