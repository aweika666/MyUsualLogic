package com.aweika.service;

import com.aweika.dao.UserMapper;
import com.aweika.dynamicDataSource.DataSourceSelector;
import com.aweika.dynamicDataSource.DynamicDataSourceEnum;
import com.aweika.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Michael
 * @date: 2020/7/10
 * @description:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @DataSourceSelector(value = DynamicDataSourceEnum.SLAVE)
    public List<User> SLAVElistUser() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @DataSourceSelector(value = DynamicDataSourceEnum.MASTER)
    public List<User> MASTERlistUser() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    public List<User> listUser() {
        List<User> users = userMapper.selectAll();
        return users;
    }


    /*@DataSourceSelector(value = DynamicDataSourceEnum.MASTER)
    public int update() {
        User user = new User();
        user.setUserId(Long.parseLong("1196978513958141952"));
        user.setUserName("修改后的名字2");
        return userMapper.updateByPrimaryKeySelective(user);
    }*/


}
