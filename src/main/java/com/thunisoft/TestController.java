package com.thunisoft;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
/**
 * Api value:值 description:描述 produces:返回值类型 MediaType.APPLICATION_JSON_VALUE json格式数据
 */
@Api(value = "TestController" , description = "描述", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {


    /**
     * ApiOperation value:方法名 httpMethod:http请求类型 GET:GET类型
     * ApiImplicitParam 请求参数 name:参数名 value: 值（描述） dataType:类型 required:参数是否必传 paramType:参数传递类型  query:GET请求参数传递类型  hander:POST  path:参数通过url传递
     * @return
     */
    @ApiOperation(value = "",httpMethod = "Get")
   // @ApiImplicitParam(name = "",value = "" , dataType = "String" , required = true ,paramType = "query")
    @RequestMapping("/hello")
    public String hello(){
        return "hello world,现在是:"+(new Date()).toLocaleString();
    }
}
