package com.thunisoft.dao;

import com.thunisoft.pojo.MasterContent;

public interface MasterContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MasterContent record);

    int insertSelective(MasterContent record);

    MasterContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MasterContent record);

    int updateByPrimaryKey(MasterContent record);
}