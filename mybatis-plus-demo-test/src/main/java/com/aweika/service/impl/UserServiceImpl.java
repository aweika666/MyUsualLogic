package com.aweika.service.impl;

import com.aweika.entity.User;
import com.aweika.mapper.UserMapper;
import com.aweika.service.IUserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FangQiZhe
 * @since 2020-09-07
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @PostConstruct
    public void method(){
        System.out.println("aaa");
    }

}
