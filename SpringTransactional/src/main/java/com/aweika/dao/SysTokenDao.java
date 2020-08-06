package com.aweika.dao;

import com.aweika.common.sys.entitySys.SysToken;


public interface SysTokenDao {

    int insert(SysToken record);

    SysToken getById(String id);

    int delete(String id);

    int update(SysToken record);
}
