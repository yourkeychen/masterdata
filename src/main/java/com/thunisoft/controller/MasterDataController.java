package com.thunisoft.controller;

import com.thunisoft.pojo.ApplicationReiew;
import com.thunisoft.pojo.MasterContent;
import com.thunisoft.pojo.Menu;
import com.thunisoft.service.MasterDataService;
import io.swagger.models.auth.In;
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
public class MasterDataController {
    @Autowired
    private MasterDataService masterDataService;

    @RequestMapping(value = {"/","/index"})
    @ResponseBody
    public String toIndex(Model model){
        Integer pId=0;
        List<Menu> menuList = masterDataService.findAllByPid(pId);
        model.addAttribute("menuList", menuList);
        return "index";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String toTest(){
        return "test";
    }


    @RequestMapping("/partMasterData")
    public Object getMasterData(Model model, @RequestParam("menuId") Integer menuId) {
        model.addAttribute("menuId",menuId);
        return "master-data";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public Object getList(@RequestParam("limit") Integer limit , @RequestParam("page") Integer page , @RequestParam("menuId") Integer menuId){
        Map<String,Object> mcMap =new HashMap<String,Object>();
        List<MasterContent> mcList=masterDataService.findMasterContent(limit,page,menuId);
        int mcCount=masterDataService.findCountByMenuId(menuId);
        mcMap.put("data",mcList);
        mcMap.put("count",mcCount);
        mcMap.put("code",0);
        mcMap.put("msg","");
        return mcMap;
    }


    @RequestMapping(value = "/saveMasterData")
    @ResponseBody
    public Object insertMasterData(@RequestParam("code") String code,@RequestParam("conentName")String conentName,@RequestParam("desc") String desc,
                                   @RequestParam("effect") Integer effect,@RequestParam("menuId") Integer menuId,@RequestParam("reason") String reason){
        MasterContent mc=new MasterContent();
        mc.setCode(code);
        mc.setConentName(conentName);
        mc.setDesc(desc);
        mc.setEffect(effect);
        mc.setMenuId(menuId);
        int mcId=masterDataService.insertMasterData(mc);
        if (mcId!=0){
            ApplicationReiew ar = new ApplicationReiew();
            ar.setReason(reason);
            ar.setMasterId(mcId);
            ar.setStatus(0); //0 待审核;
            int m =masterDataService.insertApplicationReiew(ar);
        }

        return mcId;
    }

    //查询要审核的主数据
    @RequestMapping("/getExamineMDMenu")
    public  Object toExamineMasterDataMenu(Model model,@RequestParam("menuId")Integer menuId){
        model.addAttribute("menuId",menuId);
        return "master-data-examine";
    }

    /**
     * 审核的主数据
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping("/getExamineMasterData")
    @ResponseBody
    public Object getExamineMastrData(@RequestParam("limit") Integer limit , @RequestParam("page") Integer page ){
        Map<String, Object> exMDMap = new HashMap<>();
        exMDMap.put("code",0);
        exMDMap.put("msg","");
        exMDMap.put("data",masterDataService.findExMasterData(limit,page));
        exMDMap.put("count",masterDataService.findExMasterDataCount());
        return exMDMap;
    }

}
