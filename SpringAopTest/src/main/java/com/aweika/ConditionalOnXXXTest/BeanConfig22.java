package com.aweika.ConditionalOnXXXTest;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author: Michael
 * @date: 2020/2/6
 * @description:
 */

@Configuration
public class BeanConfig22 {
    public BeanConfig22() {
        System.out.println("BeanConfig22");
    }



    @Bean("desktopPC2")
    public Computer computer3(){
        return new Computer("PC台式电脑886");
    }
}
