package com.aweika.service.impl;


import com.aweika.annotation.LogAnnotation;
import com.aweika.dao.SysUserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author: Michael
 * @date: 2020/1/21
 * @description:
 */

@Service
public class TestServiceImpl implements TestService {



    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void deleteById(Integer id) {
        sysUserDao.deleteByPrimaryKey(id);
        TestServiceImpl bean = applicationContext.getBean(TestServiceImpl.class);
        bean.deleteById_test(id+1);


    }


    public void deleteById_test(Integer id){
        sysUserDao.deleteByPrimaryKey(id);
        throw new RuntimeException("人造异常");
    }

    @Override
    @LogAnnotation(module = "哈哈哈哈哈")
    public Integer testAop() {
        System.out.println("testAop执行");
        return 1;
    }
}
