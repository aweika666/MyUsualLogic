package com.aweika.ConditionalOnXXXTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


/**
 * @author: Michael
 * @dae: 2020/2/5
 * @description:
 */

@Configuration
/*@ConditionalOnClass(name = {"com.com.aweika.ConditionalOnXXXTest.Computer886"})*/
public class BeanConfig {

    public BeanConfig() {
        System.out.println("BeanConfig");
    }

    @Autowired
    private Computer computer;

    @Bean("desktopPC")
    //@Scope("prototype")
    public Computer computer3(){
        ApplicationContext applicationContext;
        return new Computer("PC台式电脑");
    }

    /*@Bean(name = "notebookPC")
    @ConditionalOnBean(Computer.class)
    public Computer computer1(*//*Computer desktopPC*//*) {
        return new Computer("笔记本电脑");
    }*/

    /*@Bean
    public BeanConfig22 aa(){
        return new BeanConfig22();
    }*/

/*
    @ConditionalOnMissingBean(Computer.class)
    @Bean("beiyongPC")
    @DependsOn("desktopPC")

    //@Scope("prototype")
    public Computer computer2() {
        return new Computer("备用电脑");
    }


    //@ConditionalOnClass(name= {"com.com.aweika.ConditionalOnXXXTest.Computer886"})
    @Bean("newOnePC")
    public Computer computer4() {
        return new Computer("PC新的电脑");
    }*/


}
