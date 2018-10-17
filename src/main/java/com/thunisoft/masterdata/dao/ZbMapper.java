package com.thunisoft.masterdata.dao;

import com.thunisoft.masterdata.model.Zb;

import java.util.List;

public interface ZbMapper {
    int insert(Zb record);

    int insertSelective(Zb record);

    List<Zb> selectByExample(ZbExample example);
}