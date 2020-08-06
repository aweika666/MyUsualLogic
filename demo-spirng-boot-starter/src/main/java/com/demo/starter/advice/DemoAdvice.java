package com.demo.starter.advice;

import com.demo.starter.annotation.DemoAnnotation;
import com.demo.starter.redisUtil.RedisUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

/**
 * @author: Michael
 * @date: 2020/2/6
 * @description:
 */
@Aspect
public class DemoAdvice implements Ordered {
    /*@Around(value = "@annotation(com.demo.starter.annotation.DemoAnnotation)")
    public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable {
        Object flag = null;
        *//*redisUtil.set("测试","账号密码是哈哈哈",50);*//*
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String module = null;
        DemoAnnotation annotation = methodSignature.getMethod().getAnnotation(DemoAnnotation.class);
        DemoAnnotation annotation2 = methodSignature.getMethod().getDeclaredAnnotation(DemoAnnotation.class);System.out.println(annotation.message());
        System.out.println("aaaaaaa---aaaaaaa");
        try {
            flag = joinPoint.proceed();
        }catch (Exception e){
            return "异常";
        }
        System.out.println("bbbbbbb---bbbbbbb");
        return flag;
    }*/



    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return 888798;
    }

    @Autowired
    private RedisUtil redisUtil;

    static  class User{
        public String username;
        public Integer age;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Around(value = "@annotation(com.demo.starter.annotation.DemoAnnotation)")
    public Object logSave(ProceedingJoinPoint joinPoint) throws Throwable {
        User user = new User();user.age=5;user.username="启哲";
        redisTemplate.opsForHash().put("myhash2","1",user);

        //redisUtil.set("测试","账号密码是哈哈哈",50);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        DemoAnnotation annotation = methodSignature.getMethod().getAnnotation(DemoAnnotation.class);
        DemoAnnotation annotation2 = methodSignature.getMethod().getDeclaredAnnotation(DemoAnnotation.class);
        System.out.println(annotation.message());
        /*if (StringUtils.isEmpty(annotation.message())){
            throw new RuntimeException("测试异常");
        }*/
        System.out.println("aaaaaaa---aaaaaaa");
        Object flag = joinPoint.proceed();
        System.out.println("bbbbbbb---bbbbbbb");
        return flag;
    }

//    @Before(value = "@annotation(com.demo.starter.annotation.DemoAnnotation)")
    public void before(JoinPoint joinPoint){
        //*redisUtil.set("测试","账号密码是哈哈哈",50);*//*
        System.out.println("before前置通知");
    }

    //声明最终通知 不管怎样都执行
    @After(value ="@annotation(com.demo.starter.annotation.DemoAnnotation)")
    public void after(){
        System.out.println("after最终通知");
    }

    //返回后通知 正常返回会调用
    @AfterReturning(value ="@annotation(com.demo.starter.annotation.DemoAnnotation)")
    public void afterReturning(){
        System.out.println("afterReturning返回后通知");
    }
    //异常通知,抛出异常后执行
    @AfterThrowing(value ="@annotation(com.demo.starter.annotation.DemoAnnotation)", throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println(e.getMessage());
    }
}
