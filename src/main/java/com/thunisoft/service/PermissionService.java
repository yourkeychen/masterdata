package com.thunisoft.service;

import com.thunisoft.pojo.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    public int deleteByPrimaryKey(Integer id);

    public int insert(Permission record);

    public int insertSelective(Permission record);

    public Permission selectByPrimaryKey(Integer id);

    public int updateByPrimaryKeySelective(Permission record);

    public int updateByPrimaryKey(Permission record);

    public List<Permission> selectList(Map<String, Object> map);

    public int selectCount(Map<String, Object> map);

    public Permission selectByUserNamePassword(Permission record);
}
