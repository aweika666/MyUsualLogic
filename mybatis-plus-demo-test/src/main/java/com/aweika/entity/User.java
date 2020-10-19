package com.aweika.entity;

import java.io.Serializable;
import java.util.Date;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author FangQiZhe
 * @since 2020-09-07
 */

/*
AUTO	数据库自增ID                              选择这个,则mp不会传id字段
NONE	数据库未设置主键类型（将会跟随全局）
INPUT	用户输入ID（该类型可以通过自己注册自动填充插件进行填充）
ID_WORKER	全局唯一ID (idWorker)
UUID	全局唯一ID（UUID）
ID_WORKER_STR	字符串全局唯一ID（idWorker 的字符串表示）


1、如果数据库字段设成user_id
在初始生成后，在代码中会变成userId，不会设置成主键
使用**@TableId(value=“user_id”,type = IdType.AUTO)**注解

“value”：设置数据库字段值
“type”：设置主键类型、如果数据库主键设置了自增建议使用“AUTO”

type有六种类型类型，最下面三个只有插入主键为空时，才会自动填充

 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /*
     mybatis-plus只把"id"作为主键字段，名字不叫"id"的字段，需要用@TableId来标注,pkVal() 是用来支持组合主键的
     */
    /**
     * 主键ID
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;


    /**
     * 姓名
     */
    //若没有开启驼峰命名，或者表中列名不符合驼峰规则，可通过该注解指定数据库表中的列名，exist标明数据表中有没有对应列
    @TableField(value = "user_name",exist = true)
    /*
    当我将value改成 "user_name1" sql就会报错 变成下面这样
    INSERT INTO user ( id,user_name1,user_age,user_email ) VALUES ( 1303174766305640449,'老方',13,null );
     */
    private String userName;

    /**
     * 年龄
     */
    private Integer userAge;

    /**
     * 邮箱
     */
    private String userEmail;

    
    @TableField(value = "user_date")
    private Date userDate;

    @Override
    protected Serializable pkVal() {
        return id;
    }


}
