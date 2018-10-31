package com.thunisoft.service.impl;

import com.thunisoft.dao.MenuMapper;
import com.thunisoft.pojo.Menu;
import com.thunisoft.service.MasterDataService;
import com.thunisoft.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MasterDataServiceImpl implements MasterDataService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAllByPid(Integer pId) {
         List<Menu> menuList =  menuMapper.findAllByPid(pId);
        for (Menu menu:menuList) {
            menu.setChildMenus(menuMapper.findAllByPid(menu.getId()));
        }
        return menuList;
    }

}
