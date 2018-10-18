package com.thunisoft.dao;

import com.thunisoft.pojo.Zb;

public interface ZbMapper {
    int insert(Zb record);

    int insertSelective(Zb record);
}