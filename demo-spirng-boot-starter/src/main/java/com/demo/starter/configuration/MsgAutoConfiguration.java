package com.demo.starter.configuration;

import com.demo.starter.advice.DemoAdvice;
import com.demo.starter.exception.ServiceExceptionHandler;
import com.demo.starter.properties.MsgProperties;
import com.demo.starter.redisUtil.RedisUtil;
import com.demo.starter.template.MsgTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Michael
 * @date: 2020/2/6
 * @description:
 */
@Configuration
@EnableConfigurationProperties(MsgProperties.class)
@ConditionalOnProperty(prefix = "demo", name = "enabled", havingValue = "true" ,matchIfMissing = false)
@Import({DemoAdvice.class, RedisUtil.class, ServiceExceptionHandler.class})
public class MsgAutoConfiguration {
    /**
     * 注入属性配置类
     */
    @Autowired
    private MsgProperties msgProperties;

    @Bean
    @ConditionalOnMissingBean(MsgTemplate.class)
    public MsgTemplate msgTemplate(){
        MsgTemplate msgTemplate = new MsgTemplate();
        msgTemplate.setName(msgProperties.getName());
        msgTemplate.setAge(msgProperties.getAge());
        msgTemplate.setAddress(msgProperties.getAddress());
        return msgTemplate;
    }
}
