package com.aweika.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Michael
 * @description: 幂等性注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Idempotency {

    /**
     * Alias for {@link #expireTime}.

     * 单位:秒.
     */
    /*@AliasFor("expireTime")
    long value() default 0;*/

    /**
     * 此属性用以指定单独某个方法的分布式锁的失效时间.
     * 若不使用此属性,则程序默认会使用全局的失效时间.
     * 注意:使用此属性的时候,请勿将锁的失效时间设置成<1,小于1秒会抛出异常.
     */
    //@AliasFor("value")
    long expireTime() default 0;
}
