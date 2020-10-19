package com.aweika.controller;


import com.aweika.mapper.UserMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author FangQiZhe
 * @since 2020-09-07
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;


    @GetMapping("user")
    public Object user(Page page){
        page.setRecords(userMapper.selectPageByOwn(page));
        return page;
    }

}
