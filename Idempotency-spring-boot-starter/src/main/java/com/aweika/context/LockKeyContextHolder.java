package com.aweika.context;

import java.util.Map;

/**
 * @author: Michael
 * @description: 存放分布式锁
 */
public class LockKeyContextHolder {
    private volatile static ThreadLocal<Map<String,String>> threadLocal;

    private LockKeyContextHolder() {
    }

    //Double CheckLock实现单例
    private static ThreadLocal<Map<String,String>> newInstance() {
        if (threadLocal == null) {
            synchronized (LockKeyContextHolder.class) {
                if (threadLocal == null) {
                    threadLocal = new ThreadLocal<Map<String,String>>();
                }
            }
        }
        return threadLocal;
    }

    public static void setLockKey(Map<String,String> map) {
        newInstance().set(map);
    }

    public static Map<String,String> getLockKey(){
        return newInstance().get();
    }

    public static void removeLockKey(){
        newInstance().remove();
    }
}
