package com.thunisoft.service;

import com.thunisoft.pojo.SysRegistry;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface XtzcService {
    Map insertObject(SysRegistry sysRegistry);
    Map updateByPrimaryKey(SysRegistry sysRegistry);
    Map deleteObject(SysRegistry sysRegistry);
    SysRegistry selectById(Integer id);
    Integer selectXtCount();
    List<SysRegistry> selectObject(Integer pageNum,Integer pageSize);
}
