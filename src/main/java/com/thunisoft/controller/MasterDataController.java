package com.thunisoft.controller;

import com.thunisoft.pojo.MasterContent;
import com.thunisoft.pojo.Menu;
import com.thunisoft.service.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MasterDataController {
    @Autowired
    private MasterDataService masterDataService;

    @RequestMapping(value = {"/","/index"})
    public String toIndex(Model model){
        Integer pId=0;
        List<Menu> menuList = masterDataService.findAllByPid(pId);
        model.addAttribute("menuList", menuList);
        return "index";
    }

    @RequestMapping("/test")
    public String toTest(){
        return "test";
    }


    @RequestMapping("/partMasterData")
    public Object getMasterData(Model model, @RequestParam("menuId") Integer menuId) {
       /* List<MasterContent> mcList=masterDataService.findByMenuId(menuId);
        model.addAttribute("mcList", mcList);*/
        return "master-data";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public Object getList(Integer pageNum ,Integer pageStart ,@RequestParam("menuId") Integer menuId){
        Map<String,Object> mcMap =new HashMap<String,Object>();
        List<MasterContent> mcList=masterDataService.findMasterContent(pageNum,pageStart,menuId);
        int mcCount=masterDataService.findCountByMenuId(menuId);
        mcMap.put("data",mcList);
        mcMap.put("count",mcCount);
        mcMap.put("code",0);
        mcMap.put("msg","");
        return mcMap;
    }


    @RequestMapping(value = "/saveMasterData")
    @ResponseBody
    public Object insertMasterData(@RequestParam("code") String code,@RequestParam("conentName")String conentName,@RequestParam("desc") String desc,@RequestParam("effect") Integer effect){
        MasterContent mc=new MasterContent();
        mc.setCode(code);
        mc.setConentName(conentName);
        mc.setDesc(desc);
        mc.setEffect(effect);
        int i=masterDataService.insertMasterData(mc);
        return i;
    }

}
