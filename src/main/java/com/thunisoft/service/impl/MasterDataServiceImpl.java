package com.thunisoft.service.impl;

import com.thunisoft.dao.MasterContentMapper;
import com.thunisoft.dao.MenuMapper;
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
    public List<MasterContent> findMasterContent(Integer pageNum, Integer pageStart, Integer menuId) {
        return masterContentMapper.findMasterContent(pageNum,pageStart,menuId);
    }

    @Override
    public int findCountByMenuId(Integer menuId) {
        return masterContentMapper.findCountByMenuId(menuId);
    }

    @Override
    public int insertMasterData(MasterContent mc) {
        return masterContentMapper.insert(mc);
    }


}
