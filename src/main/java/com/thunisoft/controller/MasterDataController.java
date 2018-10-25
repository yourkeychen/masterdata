package com.thunisoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/masterData")
public class MasterDataController {

    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }
}
