package com.thunisoft;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
@RequestMapping("/masterdata")
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello world,现在是:"+(new Date()).toLocaleString();
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
