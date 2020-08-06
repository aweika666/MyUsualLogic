package com.aweika.common.utils;


import com.alibaba.fastjson.JSONObject;
import com.aweika.common.sys.entitySys.LoginUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



/**
 * @author weiyunjie
 * @date 2019/9/5
 */
public class UserUtilFromRequest {

    public static LoginUser getUser(){
        String loginUserString = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
                .getHeader("loginUser");
        LoginUser loginUser = JSONObject.parseObject(loginUserString,LoginUser.class);
        return loginUser;
    }
}
