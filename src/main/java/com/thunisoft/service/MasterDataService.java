package com.thunisoft.service;

import com.thunisoft.pojo.ApplicationReiew;
import com.thunisoft.pojo.MasterContent;
import com.thunisoft.pojo.Menu;

import java.util.List;
import java.util.Map;


public interface MasterDataService {

    List<Menu> findAllByPid(Integer pId);

    List<MasterContent> findByMenuId(Integer menuId);

    List<MasterContent> findMasterContent(Integer limit, Integer page, Integer menuId);

    int findCountByMenuId(Integer menuId);

    int insertMasterData(MasterContent mc);

    int insertApplicationReiew(ApplicationReiew ar);

    List<Map> findExMasterData(Integer limit, Integer page,Integer status);

    int findExMasterDataCount();

    int updateExaminDataById(ApplicationReiew appRe);
}
