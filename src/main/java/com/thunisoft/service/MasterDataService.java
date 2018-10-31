package com.thunisoft.service;

import com.thunisoft.pojo.Menu;

import java.util.List;


public interface MasterDataService {

    List<Menu> findAllByPid(Integer pId);
}
