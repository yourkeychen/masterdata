package com.thunisoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MasterDataController {

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("/test")
    public String toTest(){
        return "test";
    }
}
