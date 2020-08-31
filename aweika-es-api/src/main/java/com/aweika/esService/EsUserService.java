package com.aweika.esService;

import com.aweika.entity.UserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author: Michael
 * @date: 2020/8/19
 * @description:
 * 第一个就是所准备的实体类，第二个是id的类型
 * 继承完这个会提供最基本的增删改查方法，也可以自己定义一些，自己定义的方法命名需要!!!!!<符合规则>!!!!</符合规则>，并不需要自己去实现
 */
public interface EsUserService extends ElasticsearchRepository<UserEntity,Integer> {
    //根据name查询
    List<UserEntity> findByName(String name);

    //根据name和info查询
    List<UserEntity> findByNameAndInfo(String name,String info);

    //根据info查询
    List<UserEntity> findByInfo(String info);
}
