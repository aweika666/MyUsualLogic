package com.aweika;

import com.aweika.service.WebSocketServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Michael
 * @date: 2020/8/26
 * @description:
 */
@SpringBootApplication
public class WebSocketServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketServerApplication.class,args);
    }
}
