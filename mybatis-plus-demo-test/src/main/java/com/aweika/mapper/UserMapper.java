package com.aweika.mapper;

import com.aweika.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FangQiZhe
 * @since 2020-09-07
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> selectPageByOwn(Page page);

    Integer insertByOwn(User user);

    List<User> select();

}
