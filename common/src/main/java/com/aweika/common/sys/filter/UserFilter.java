package com.aweika.common.sys.filter;

import com.alibaba.fastjson.JSONObject;
import com.aweika.common.sys.entitySys.SysUser;
import com.aweika.common.sys.threadLocal.UserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Author: Fangqizhe
 * @Date: 2019/9/4 6:09 PM
 * @Description: 解析上游zuul传递过来的sysUser的Json串
 */
@Component
public class UserFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //测试
        String token = getToken(request);

        //取出上游服务器 zuul 传过来的loginUser对象的json字符串
        String userJson = request.getHeader("loginUser");
        if (StringUtils.isNotBlank(userJson)) {
            SysUser sysUser = JSONObject.parseObject(URLDecoder.decode(userJson, "utf-8"), SysUser.class);
            //放入线程隔离的容器中
            UserUtil.setSysUser(sysUser);
        }
        filterChain.doFilter(request, response);
        //清除
        UserUtil.clearSysUser();
        //SysUser sysUser = UserUtil.getSysUser();
    }


    /**
     * 根据参数或者header获取token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)) {
            token = request.getHeader("token");
        }
        if (StringUtils.isEmpty(token)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("token".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }
        return token;
    }

}