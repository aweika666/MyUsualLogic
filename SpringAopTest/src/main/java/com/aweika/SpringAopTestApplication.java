package com.aweika;

import com.aweika.advice.LogAdvice;
import com.aweika.service.impl.TestServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: Michael
 * @date: 2020/2/2
 * @description:
 */
@MapperScan(value = "com.com.aweika.dao")
@SpringBootApplication
@Import({LogAdvice.class, TestServiceImpl.class})
public class SpringAopTestApplication {
    public static void main(String[] args) {

    }
}
