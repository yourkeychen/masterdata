package com.thunisoft.dao;

import com.thunisoft.pojo.SysRegistry;

public interface SysRegistryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRegistry record);

    int insertSelective(SysRegistry record);

    SysRegistry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRegistry record);

    int updateByPrimaryKey(SysRegistry record);
}