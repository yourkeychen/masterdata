package com.thunisoft.dao;

import com.thunisoft.pojo.MasterContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MasterContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MasterContent mc);

    int insertSelective(MasterContent record);

    MasterContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MasterContent record);

    int updateByPrimaryKey(MasterContent record);

    List<MasterContent> findByMenuId(Integer menuId);

    List<MasterContent> findMasterContent(@Param("limit")Integer limit, @Param("page")Integer page, @Param("menuId") Integer menuId);

    int findCountByMenuId(Integer menuId);

    int findExMasterDataCount();

    List<Map> findExMasterData(@Param("limit") Integer limit, @Param("page") Integer page,@Param("status") Integer status);
}
