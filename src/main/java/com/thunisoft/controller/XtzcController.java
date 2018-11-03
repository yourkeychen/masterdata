package com.thunisoft.controller;

import com.thunisoft.dao.SysRegistryMapper;
import com.thunisoft.pojo.SysRegistry;
import com.thunisoft.service.XtzcService;
import com.thunisoft.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/masterData")
public class XtzcController {
    @Autowired
    private SysRegistryMapper sysRegistryMapper;
    @Autowired
    private XtzcService xtzcService;
    @RequestMapping("/toxtzc")
    public String toXtzc(){
        return "xtzc";
    }
    @RequestMapping("/tolog")
    public String tolog(){
        return "loginform";
    }
    @RequestMapping("/toAddWindow")
    public String toAddWindow(@RequestParam Map<String,Object> map, Model model){
        if(map.get("id")!=null){
            SysRegistry sysRegistry = xtzcService.selectById(Integer.valueOf((String) map.get("id")));
            model.addAttribute("sysRegistry",sysRegistry);
        }else {
            model.addAttribute("sysRegistry",new SysRegistry());
        }
        return "XtzcInsert";
    }
    @RequestMapping("/showObjectsq")
    @ResponseBody
    public Object showObjects1(Integer currentPage,Integer pageSize){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("code",0);
        stringObjectHashMap.put("msg","");
        stringObjectHashMap.put("count",sysRegistryMapper.selectXtCount());
        stringObjectHashMap.put("data",sysRegistryMapper.selectObject(currentPage,pageSize));
        return stringObjectHashMap;
    }
    @RequestMapping("/insertObject")
    @ResponseBody
    public Object insertObject(SysRegistry sysRegistry){
        return CommonUtils.getJsonRes(xtzcService.insertObject(sysRegistry));
    }
    @RequestMapping("/updateObject")
    @ResponseBody
    public Object updateObject(SysRegistry sysRegistry){
        return CommonUtils.getJsonRes(xtzcService.updateByPrimaryKey(sysRegistry));
    }
    @RequestMapping("/deleteObject")
    @ResponseBody
    public Object deleteObject(SysRegistry sysRegistry){
        return CommonUtils.getJsonRes(xtzcService.deleteObject(sysRegistry));
    }

}
