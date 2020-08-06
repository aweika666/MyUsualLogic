package com.aweika;

import com.aweika.web.TestWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: Michael
 * @date: 2020/2/2
 * @description:
 */
@MapperScan(value = "com.com.aweika.dao")
@SpringBootApplication
@Import({TestWeb.class})
public class DemoTestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoTestApplication.class);
    }

    /*@Bean("testWeb2")
    public TestWeb testWeb(){
        TestWeb testWeb = new TestWeb();
        return testWeb;
    }*/

}
