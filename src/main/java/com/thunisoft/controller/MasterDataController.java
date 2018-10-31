package com.thunisoft.controller;

import com.thunisoft.pojo.Menu;
import com.thunisoft.service.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MasterDataController {
    @Autowired
    private MasterDataService masterDataService;

    @RequestMapping(value = {"/","/index"})
    public String toIndex(Model model){
        Integer pId=0;
        List<Menu> menuList = masterDataService.findAllByPid(pId);
        model.addAttribute("menuList", menuList);
        return "index";
    }

    @RequestMapping("/test")
    public String toTest(){
        return "test";
    }
}
