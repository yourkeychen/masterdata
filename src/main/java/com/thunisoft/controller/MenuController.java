package com.thunisoft.controller;

import com.thunisoft.pojo.Menu;
import com.thunisoft.service.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MasterDataService masterDataService;

    @RequestMapping("/data")
    public String getMasterDataMenu(Model model, @RequestParam(value = "pId") Integer pId) {
        List<Menu> menuList = masterDataService.findAllByPid(pId);
        model.addAttribute("menuList", menuList);

        return "index";
    }

    @RequestMapping("/homePage")
    public String homePage() {

        return "home-page";
    }




}