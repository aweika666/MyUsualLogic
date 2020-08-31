package com.aweika.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: Michael
 * @date: 2020/8/19
 * @description:
 */
@Data
@Document(indexName = "user_entity")//索引名称 建议与实体类一致
public class UserEntity {
    private Integer id;
    /*@Field(type = FieldType.Long)//自动检测类型
    private Integer userId;*/
    @Field(type = FieldType.Long)//自动检测类型
    private Integer age;
    @Field(type = FieldType.Keyword)//手动设置为keyword  但同时也就不能分词
    private String name;
    @Field(type = FieldType.Text,analyzer = "ik_smart",searchAnalyzer = "ik_max_word")//设置为text  可以分词
    private String info;
}
