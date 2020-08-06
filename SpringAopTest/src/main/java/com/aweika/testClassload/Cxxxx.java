package com.aweika.testClassload;

import com.aweika.testClassload.classload.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Michael
 * @date: 2020/2/18
 * @description:
 */
@Configuration
public class Cxxxx {


    @Autowired
    private User user;


    @Bean
    public User getUser(){
        User user = new User();
        user.setName(this.user);
        return user;
    }
}
