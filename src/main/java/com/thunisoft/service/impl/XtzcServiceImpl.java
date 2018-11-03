package com.thunisoft.service.impl;

import com.thunisoft.dao.SysRegistryMapper;
import com.thunisoft.pojo.SysRegistry;
import com.thunisoft.service.XtzcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class XtzcServiceImpl implements XtzcService {
    @Autowired
    private SysRegistryMapper sysRegistryMapper;
    @Override
    public Map insertObject(SysRegistry sysRegistry) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        int insert = sysRegistryMapper.insert(sysRegistry);
        if (insert>0){
            stringObjectHashMap.put("success",true);
        }else {
            stringObjectHashMap.put("success",false);
        }
        return stringObjectHashMap;
    }

    @Override
    public Map updateByPrimaryKey(SysRegistry sysRegistry) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        int update = sysRegistryMapper.updateByPrimaryKey(sysRegistry);
        if (update>0){
            stringObjectHashMap.put("success",true);
        }else {
            stringObjectHashMap.put("success",false);
        }
        return stringObjectHashMap;
    }

    @Override
    public Map deleteObject(SysRegistry sysRegistry) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        int update = sysRegistryMapper.deleteByPrimaryKey(sysRegistry.getId());
        if (update>0){
            stringObjectHashMap.put("success",true);
        }else {
            stringObjectHashMap.put("success",false);
        }
        return stringObjectHashMap;
    }

    @Override
    public SysRegistry selectById(Integer id) {
        SysRegistry sysRegistry = sysRegistryMapper.selectByPrimaryKey(id);
        return sysRegistry;
    }
}
