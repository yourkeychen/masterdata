package com.thunisoft.dao;

import com.thunisoft.pojo.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> selectList(Map<String, Object> map);

    int selectCount(Map<String, Object> map);

    Permission selectByUserNamePassword(Permission record);

    Permission selectByUserName(Map<String,Object> map);

}