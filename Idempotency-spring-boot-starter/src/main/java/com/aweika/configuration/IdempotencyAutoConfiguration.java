package com.aweika.configuration;

import com.aweika.advice.IdempotencyAdvice;
import com.aweika.properties.IdempotencyProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;

/**
 * @author: Michael
 * @description: 幂等性Starter自动配置类
 */
@Configuration
@EnableConfigurationProperties(IdempotencyProperties.class)
@ConditionalOnProperty(prefix = "idempotency", name = "enabled", havingValue = "true", matchIfMissing = false)
@Import({IdempotencyAdvice.class})
public class IdempotencyAutoConfiguration {

    @Autowired
    private RedisTemplate redisTemplate;

    //防止乱码
    @PostConstruct
    public void stringSerializerRedisTemplate() {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
    }


}
