package com.thunisoft.masterdata.dao;

import com.thunisoft.masterdata.pojo.Zb;

public interface ZbMapper {
    int insert(Zb record);

    int insertSelective(Zb record);
}