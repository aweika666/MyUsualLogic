package com.aweika.common.sys.entitySys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 角色
 */
public class SysRole {
    //角色id
    private Integer id;
    //角色名
    private String name;
    //角色描述
    private String description;
    //创建者id
    @JsonIgnore
    private Integer createId;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    //是否是管理员 1- 是
    @JsonIgnore
    private Integer isAdmin;
    //单位级别 1-市 2-区县 3-乡镇街道 4-企业 5-小微园区(目前用不到)
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class<SysRole> roleClass = SysRole.class;
        Method method = roleClass.getMethod("getName");
        Class returnType = method.getReturnType();
        Object cast = returnType.cast(Integer.valueOf(2));
    }

}