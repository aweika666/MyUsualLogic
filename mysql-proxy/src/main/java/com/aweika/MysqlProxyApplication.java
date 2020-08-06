package com.aweika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: Michael
 * @date: 2020/7/4
 * @description:
 */
//@MapperScan(value = "com.com.aweika.dao")
@SpringBootApplication
public class MysqlProxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlProxyApplication.class);
    }
}
