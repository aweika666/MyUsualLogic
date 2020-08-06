package com.aweika.testClassload.classload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Michael
 * @date: 2020/2/16
 * @description:
 */
//@Component
public class User {


    private Object name;

    public Object getName() {
        return name;
    }

    public User setName(Object name) {
        this.name = name;
        return this;
    }
}
