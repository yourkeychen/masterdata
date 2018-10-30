package com.thunisoft.controller;

import com.thunisoft.pojo.Permission;
import com.thunisoft.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> getList(@RequestParam Map<String,Object> map){
        Map<String,Object> result = new HashMap<>();
        List<Permission> list = permissionService.selectList(map);
        int count = permissionService.selectCount(map);
        result.put("list",list);
        result.put("count",count);
        return result;
    }
}
