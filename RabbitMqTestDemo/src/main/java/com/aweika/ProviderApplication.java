package com.aweika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: Michael
 * @date: 2020/7/30
 * @description:
 */
@MapperScan(value = "com.com.aweika.dao")
@SpringBootApplication
public class ProviderApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ProviderApplication.class);
    }
}
