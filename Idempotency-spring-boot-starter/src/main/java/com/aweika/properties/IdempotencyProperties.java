package com.aweika.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.annotation.PostConstruct;

/**
 * @author: Michael
 * @description: 全局参数配置
 */
@ConfigurationProperties(prefix = "idempotency.configuration")
public class IdempotencyProperties {

    /*@Autowired
    private ApplicationContext applicationContext;*/

    /*
    全局的幂等接口超时时间,默认时间是1s.
    自定义配置的时间不能小于1s,否则将会抛出异常.
     */
    private long globalExpireTime = 1;

    public long getGlobalExpireTime() {
        return globalExpireTime;
    }

    public IdempotencyProperties setGlobalExpireTime(long globalExpireTime) {
        this.globalExpireTime = globalExpireTime;
        return this;
    }

    /*
          检查globalTimeout,不能小于1s.
        */
    @PostConstruct
    public void checkGlobalExpireTime(){
        if (this.globalExpireTime < 1)
            throw new RuntimeException("幂等性配置的全局超时时间不能小于1秒");
    }
}
