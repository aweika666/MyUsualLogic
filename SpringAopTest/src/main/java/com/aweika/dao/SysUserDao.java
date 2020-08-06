package com.aweika.dao;


import com.aweika.common.sys.entitySys.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findByUsername(String username);

    List<SysUser> findByRoleId(Integer roleId);

/*    List<SysUser> govUserList(SysUserDto sysUserDto);*/

    List<SysUser> findByMobile(String mobile);

    void updatePassword(@Param("id") Long id, @Param("password") String password);

    void updatePhone(@Param("id") Long id, @Param("newMobile") String newMobile);

    List<Map>  findByUnitLevel(@Param("unitLevel") Integer unitLevel);
}
