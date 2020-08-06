package com.ConfigurationPropertiesTest;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Michael
 * @date: 2020/2/5
 * @description: @
 */
@ConfigurationProperties(prefix = "mytest")
public class MytestBean {
    private String name ;
    private Integer age;

    public String getName() {
        return name;
    }

    public MytestBean setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public MytestBean setAge(Integer age) {
        this.age = age;
        return this;
    }
}
