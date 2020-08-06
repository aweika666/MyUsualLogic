package com.aweika.common.sys.entitySys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @Author: Fangqizhe
 * @Date: 2019/9/9 7:54 PM
 * @Description: 权限 角色 多对多的中间表
 */
public class SysRolePermission {
    //角色id
    private Integer roleId;
    //权限id
    private Integer permissionId;
    @JsonIgnore
    private Date updateTime;
    @JsonIgnore
    private Date createTime;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
