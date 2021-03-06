package com.thunisoft.dao;

import com.thunisoft.pojo.SysRegistry;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysRegistryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRegistry record);

    int insertSelective(SysRegistry record);

    SysRegistry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRegistry record);

    int updateByPrimaryKey(SysRegistry record);
    Integer selectCount ();
    List<SysRegistry> selectObject(@Param("pageNum") Integer pageNum, @Param("pageSize")Integer pageSize);
    Integer selectXtCount();
    List<Map> selectLogObject(@Param("currentPage")Integer currentPage,@Param("pageSize")Integer pageSize);

    Integer selectCountPagesize();
}