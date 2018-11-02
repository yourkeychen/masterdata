package com.thunisoft.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thunisoft.pojo.Permission;
import com.thunisoft.service.PermissionService;
import com.thunisoft.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/toPermission")
    public String toPermission(){
        return "permission";
    }

    @RequestMapping("/toInsert")
    public String toInsert(@RequestParam Map<String,Object> map, Model model){
        if(map.get("id") != null){
            Integer id = Integer.valueOf((String) map.get("id"));
            Permission per = permissionService.selectByPrimaryKey(id);
            model.addAttribute("permission",per);
        }else{
            model.addAttribute("permission",new Permission());
        }
        return "permissionInsert";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> getList(@RequestParam Map<String,Object> map){
        Map<String,Object> result = new HashMap<>();
        List<Permission> list = permissionService.selectList(map);
        int count = permissionService.selectCount(map);
        result.put("data",list);
        result.put("count",count);
        result.put("code",0);
        result.put("msg","");
        return result;
    }

    @RequestMapping("/insert")
    @ResponseBody
    public JSONObject insert(@RequestParam Map<String,Object> map){
        Permission permission = new Permission();
        permission.setUserName((String) map.get("username"));
        permission.setPassword((String) map.get("password"));
        permission.setType(Short.valueOf((String) map.get("type")));
        return CommonUtils.getJsonRes(permissionService.insertSelective(permission));
    }

    @RequestMapping("/update")
    @ResponseBody
    public JSONObject update(@RequestParam Map<String,Object> map){
        Permission permission = new Permission();
        permission.setUserName((String) map.get("username"));
        permission.setPassword((String) map.get("password"));
        permission.setType(Short.valueOf((String) map.get("type")));
        permission.setId(Integer.valueOf((String) map.get("id")));
        return CommonUtils.getJsonRes(permissionService.updateByPrimaryKey(permission));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JSONObject delete(@RequestParam Map<String,Object> map){
        return CommonUtils.getJsonRes(permissionService.deleteByPrimaryKey(Integer.valueOf((String) map.get("id"))));
    }
}
