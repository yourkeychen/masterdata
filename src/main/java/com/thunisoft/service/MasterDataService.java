package com.thunisoft.service;

import com.thunisoft.pojo.MasterContent;
import com.thunisoft.pojo.Menu;

import java.util.List;
import java.util.Map;


public interface MasterDataService {

    List<Menu> findAllByPid(Integer pId);

    List<MasterContent> findByMenuId(Integer menuId);

    List<MasterContent> findMasterContent(Integer pageNum, Integer pageStart, Integer menuId);

    int findCountByMenuId(Integer menuId);

    int insertMasterData(MasterContent mc);
}
