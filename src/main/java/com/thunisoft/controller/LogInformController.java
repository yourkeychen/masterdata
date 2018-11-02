package com.thunisoft.controller;

import com.thunisoft.dao.SysRegistryMapper;
import com.thunisoft.pojo.SysRegistry;
import com.thunisoft.service.XtzcService;
import com.thunisoft.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/loginform")
public class LogInformController {
    @Autowired
    private SysRegistryMapper sysRegistryMapper;
    @Autowired
    private XtzcService xtzcService;
    @RequestMapping("/tolog")
    public String tolog(){
        return "loginform";
    }
    @RequestMapping("/showLogObject")
    @ResponseBody
    public Object showLogObject(Integer currentPage,Integer pageSize){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("code",0);
        stringObjectHashMap.put("msg","");
        stringObjectHashMap.put("count",sysRegistryMapper.selectCountPagesize(currentPage,pageSize));
        stringObjectHashMap.put("data",sysRegistryMapper.selectLogObject(currentPage,pageSize));
        return stringObjectHashMap;
    }

}
