package com.thunisoft.service.impl;

import com.thunisoft.dao.PermissionMapper;
import com.thunisoft.pojo.Permission;
import com.thunisoft.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission record) {
        return permissionMapper.insert(record);
    }

    @Override
    public int insertSelective(Permission record) {
        return permissionMapper.insertSelective(record);
    }

    @Override
    public Permission selectByPrimaryKey(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Permission record) {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Permission record) {
        return permissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Permission> selectList(Map<String,Object> map) {
        return permissionMapper.selectList(map);
    }

    @Override
    public int selectCount(Map<String, Object> map) {
        return permissionMapper.selectCount(map);
    }

    @Override
    public Permission selectByUserNamePassword(Permission record) {
        return permissionMapper.selectByUserNamePassword(record);
    }
}
