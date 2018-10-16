package com.thunisoft.masterdata;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    String hello(){
        return "hello world,现在是:"+(new Date()).toLocaleString();
    }
}
