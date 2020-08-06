package com.aweika.advice;


import com.alibaba.fastjson.JSON;
import com.aweika.annotation.Idempotency;
import com.aweika.context.LockKeyContextHolder;
import com.aweika.properties.IdempotencyProperties;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: Michael
 * @date: 2020/2/16
 * @description: 幂等性接口, 切面
 */
@Aspect
public class IdempotencyAdvice {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IdempotencyProperties idempotencyProperties;

    /*
     前置
     */
    @Before(value = "@annotation(com.aweika.annotation.Idempotency)")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //如果切的是实现接口的controller,则realMethod != targetMethod,切的无实现的controller则 相等
        Method targetMethod = methodSignature.getMethod();
        Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(targetMethod.getName(), targetMethod.getParameterTypes());

        //定义锁的超时时间
        long timeout = 0;
        //获取注解信息
        Idempotency idempotency = realMethod.getAnnotation(Idempotency.class);
        long expireTime = idempotency.expireTime();

        if (expireTime != 0) {
            //使用注解里配置的锁的超时时间
            if (expireTime < 1)
                throw new RuntimeException("分布锁的超时时间不能小于1秒");
            else
                timeout = expireTime;
        } else {
            //使用全局的配置
            timeout = idempotencyProperties.getGlobalExpireTime();
        }
        //分布式锁
        String lockName = JSON.toJSONString(joinPoint.getArgs()) + ":" + 2;//模拟用户id是2
        String lockValue = String.valueOf(System.currentTimeMillis()) + ":" + 2;//模拟用户id是2
        Map<String, String> map = new HashMap<>();
        map.put("lockName", lockName);
        map.put("lockValue", lockValue);
        LockKeyContextHolder.setLockKey(map);

        tryGetLock(timeout);
    }

    /**
     * 尝试取
     *
     * @param timeout   超时时间
     */
    private void tryGetLock(long timeout) {
        Map<String, String> lock = LockKeyContextHolder.getLockKey();
        String lockName = lock.get("lockName");
        String lockValue = lock.get("lockValue");
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(lockName, lockValue, timeout, TimeUnit.SECONDS);
        if (!flag) {
            //加锁失败,则抛出异常提示
            throw new RuntimeException("请勿在1秒钟之内多次请求");
        }
    }


    /*
     最终通知
     */
    @After(value = "@annotation(com.aweika.annotation.Idempotency)")
    public void after(JoinPoint joinPoint) {
        Map<String, String> lock = LockKeyContextHolder.getLockKey();

        if (lock != null){
            //取出锁名和锁值,进行比对
            String lockName = lock.get("lockName");
            String lockValue = lock.get("lockValue");
            LockKeyContextHolder.removeLockKey();

            Object result = redisTemplate.opsForValue().get(lockName);
            if (result != null) {
                //3种情况,1:当前线程执行时间过久,其所持有的锁已经释放,则取出的lockValue的值和自己的lockValue不相同,不对这个锁做任何操作.
                //       2:当前线程是个重复请求线程,根本就没有拿到锁,tryGetLock()里面已经抛出异常,则取出的lockValue的值和自己的lockValue不相同,不对这个锁做任何操作.
                //       3:当前线程已经执行完了,并且锁还没有过期,则取出的lockValue的值和自己的lockValue相同,那么再删除锁.
                if (lockValue.equals(result)) {
                    realseLock(lockName);
                }
            }
            //result == null : 则锁已过期,持有锁的线程刚刚执行完,并且没有其他线程/进程 取得锁,不用任何操作.
        }

    }

    /**
     * 释放锁
     *
     * @param lockName 锁名
     */
    private void realseLock(String lockName) {
        redisTemplate.delete(lockName);
    }


}
