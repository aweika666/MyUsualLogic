package com.aweika.common.sys.threadLocal;

import com.aweika.common.sys.entitySys.SysUser;

/**
 * @Author: Fangqizhe
 * @Description: 线程级别: 封装对象 上游zuul验证通过后传递过来SysUser对象
 */
public class UserUtil {

    private static  ThreadLocal<SysUser> sysUserHolder = new ThreadLocal<>();

    //取
    public static SysUser getSysUser(){
        SysUser sysUser = sysUserHolder.get();
        //if (sysUser == null) throw new RuntimeException("认证失败,sysUser为null");
        //sysUserHolder.remove();//清除 mean:只能用一次
        return sysUser;
    }

    //赋
    public static void setSysUser(SysUser sysUser){
        sysUserHolder.set(sysUser);
    }

    //清除
    public static void clearSysUser(){
        sysUserHolder.remove();
    }

}
