package com.thunisoft.dao;

import com.thunisoft.pojo.MasterContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MasterContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MasterContent mc);

    int insertSelective(MasterContent record);

    MasterContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MasterContent record);

    int updateByPrimaryKey(MasterContent record);

    List<MasterContent> findByMenuId(Integer menuId);

    List<MasterContent> findMasterContent(@Param("pageNum")Integer pageNum, @Param("pageStart")Integer pageStart, @Param("menuId") Integer menuId);

    int findCountByMenuId(Integer menuId);
}
