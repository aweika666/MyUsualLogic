package com.aweika.ConditionalOnXXXTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Michael
 * @date: 2020/2/21
 * @description:
 */
@Configuration
public class BeanConfig33 {

    public BeanConfig33() {
        System.out.println("BeanConfig33");
    }



    @Bean("desktopPC33")
    public Computer computer3(){
        return new Computer("PC台式电脑333");
    }
}
