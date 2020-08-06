package com.aweika.dao;

import com.aweika.common.sys.entitySys.SysPermission;

import java.util.List;

/**
 * @Author: Fangqizhe
 * @Date: 2019/9/8 6:26 PM
 * @Description:
 */
public interface SysPermissionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> listByRoleId(Long roleId);
}
