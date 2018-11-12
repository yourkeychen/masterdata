package com.thunisoft.service.impl;

import com.thunisoft.dao.ApplicationReiewMapper;
import com.thunisoft.dao.MasterContentMapper;
import com.thunisoft.dao.MenuMapper;
import com.thunisoft.pojo.ApplicationReiew;
import com.thunisoft.pojo.MasterContent;
import com.thunisoft.pojo.Menu;
import com.thunisoft.service.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MasterDataServiceImpl implements MasterDataService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MasterContentMapper masterContentMapper;
    @Autowired
    private ApplicationReiewMapper applicationReiewMapper;


    @Override
    public List<Menu> findAllByPid(Integer pId) {
         List<Menu> menuList =  menuMapper.findAllByPid(pId);
        for (Menu menu:menuList) {
            menu.setChildMenus(menuMapper.findAllByPid(menu.getId()));

        }
        return menuList;
    }

    @Override
    public List<MasterContent> findByMenuId(Integer menuId) {
         List<MasterContent> mcList=masterContentMapper.findByMenuId(menuId);
        return mcList;
    }

    @Override
    public List<MasterContent> findMasterContent(Integer limit, Integer page, Integer menuId) {
        return masterContentMapper.findMasterContent(limit,page,menuId);
    }

    @Override
    public int findCountByMenuId(Integer menuId) {
        return masterContentMapper.findCountByMenuId(menuId);
    }

    @Override
    public int insertMasterData(MasterContent mc) {
        if (masterContentMapper.insert(mc)>0){
            return mc.getId();
        }else {
            return 0;
        }
    }

    @Override
    public int insertApplicationReiew(ApplicationReiew ar) {

        return applicationReiewMapper.insert(ar);
    }

    @Override
    public List<Map> findExMasterData(Integer limit, Integer page,Integer status) {
        return masterContentMapper.findExMasterData(limit,page,status);
    }

    @Override
    public int findExMasterDataCount(Integer status) {

        return masterContentMapper.findExMasterDataCount(status);
    }

    @Override
    public int updateExaminDataById(ApplicationReiew appRe) {
        return applicationReiewMapper.updateByPrimaryKey(appRe);
    }


}
