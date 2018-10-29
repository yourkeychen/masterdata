package com.thunisoft.dao;

import com.thunisoft.pojo.ApplicationReiew;

public interface ApplicationReiewMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApplicationReiew record);

    int insertSelective(ApplicationReiew record);

    ApplicationReiew selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApplicationReiew record);

    int updateByPrimaryKey(ApplicationReiew record);
}