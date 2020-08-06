package com.aweika.web;

import com.aweika.annotation.Idempotency;
import com.aweika.redis.RedisUtil;

import com.aweika.response.WebResponse;
import com.aweika.service.impl.TestService;
import com.demo.starter.annotation.DemoAnnotation;
import com.demo.starter.template.MsgTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author: Michael
 * @date: 2020/2/2
 * @description:
 */
@RequestMapping
@RestController
@Validated
public class TestWeb {

    @Autowired
    private TestService testService;

    @Autowired
    private MsgTemplate msgTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RedisUtil redisUtil;


    @GetMapping("testDemoStarter")
    @DemoAnnotation
    public Object testDemoStarter(@NotBlank String name){
        Map<String, TestWeb> beansOfType = applicationContext.getBeansOfType(TestWeb.class);
        System.out.println(applicationContext.getBean(RedisUtil.class));
        System.out.println(applicationContext.getBean(com.demo.starter.redisUtil.RedisUtil.class));
        return WebResponse.resSuccess("成功", msgTemplate.getMessage());
    }


    @GetMapping("testIdempotency")
    @Idempotency(expireTime = 1)
    public Object testIdempotency(String name) throws InterruptedException {
        Thread.sleep(10000);
        return WebResponse.resSuccess("成功",name);
    }

}
