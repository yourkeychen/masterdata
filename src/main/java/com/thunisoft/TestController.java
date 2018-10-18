package com.thunisoft;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world,现在是:"+(new Date()).toLocaleString();
    }
}
