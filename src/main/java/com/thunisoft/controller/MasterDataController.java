package com.thunisoft.controller;

import com.alibaba.fastjson.JSONObject;
import com.thunisoft.pojo.ApplicationReiew;
import com.thunisoft.pojo.MasterContent;
import com.thunisoft.pojo.Menu;
import com.thunisoft.pojo.Permission;
import com.thunisoft.service.MasterDataService;
import com.thunisoft.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class MasterDataController {
    /*待审核状态*/
    private static final Integer PENDREVIEWSTATUS=0;

    @Autowired
    private MasterDataService masterDataService;

    @Autowired
    private HttpSession session;

    @RequestMapping(value = "/homePage")
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

    /**
     * 新增主数据
     * @param code
     * @param contentName
     * @param desc
     * @param effect
     * @param menuId
     * @param reason
     * @return
     */
    @RequestMapping(value = "/saveMasterData")
    @ResponseBody
    public Object insertMasterData(@RequestParam("code") String code,@RequestParam("contentName")String contentName,@RequestParam("desc") String desc,
                                   @RequestParam("effect") Integer effect,@RequestParam("menuId") Integer menuId,@RequestParam("reason") String reason){
        JSONObject rtn = new JSONObject();
        MasterContent mc=new MasterContent();
        mc.setCode(code);
        mc.setContentName(contentName);
        mc.setDesc(desc);
        mc.setEffect(effect);
        mc.setMenuId(menuId);
        Permission permission= (Permission) session.getAttribute("user");
       if (Validator.isNotNullOrEmpty(permission)){
            mc.setCreator(permission.getUserName());
        }
        int mcId=masterDataService.insertMasterData(mc);
        int arId = 0;
        if (mcId!=0){
            ApplicationReiew ar = new ApplicationReiew();
            ar.setReason(reason);
            ar.setMasterId(mcId);
            ar.setStatus(0); //0 待审核;
            ar.setApplicant(mc.getCreator());
            arId =masterDataService.insertApplicationReiew(ar);
        }
        rtn.put("result",arId);
        if (arId>0){
            rtn.put("success",true);
        }else {
            rtn.put("success",false);
        }
        return rtn;
    }

    //查询要审核的主数据
    @RequestMapping("/getExamineMDMenu")
    public  Object toExamineMasterDataMenu(Model model,@RequestParam("menuId")Integer menuId){
        model.addAttribute("menuId",menuId);
        return "master-data-examine";
    }

    /**
     * 重定向到增加主数据页面
     * @param model
     * @return
     */
    @RequestMapping("/redirectMasterDataAdd")
    public  Object redirectMasterDataAdd(Model model,@RequestParam("menuId") Integer menuId){
        model.addAttribute("menuId",menuId);
        return "master-data-add";
    }
    /**
     * 重定向到增加主数据页面
     * @param
     * @return
     */
    @RequestMapping("/redirectExamineShow")
    public  Object redirectExamineShow(Model model,@RequestParam("status") Integer status,@RequestParam("id") Integer id){
         model.addAttribute("status",status);
         model.addAttribute("id",id);
        return "master-data-examine-box";
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
        Map<String, Object> exMDMap =new HashMap<>();
        exMDMap.put("code",0);
        exMDMap.put("msg","");
        exMDMap.put("data",masterDataService.findExMasterData(limit,page,PENDREVIEWSTATUS));
        exMDMap.put("count",masterDataService.findExMasterDataCount(PENDREVIEWSTATUS));
        return exMDMap;
    }
    /**
     * 提交审核状态
     * @param
     * @param
     * @return
     */
    @RequestMapping("/updateExaminStatus")
    @ResponseBody
    public Object updateExaminStatus(HttpServletRequest request, HttpServletResponse response,@RequestParam("id") Integer id ,
                                     @RequestParam("status") Integer status, @RequestParam("auditOptnion") String auditOptnion){
        JSONObject jb = new JSONObject();
        int appId=0;
        ApplicationReiew appRe=new ApplicationReiew();
        appRe.setId(id);
        appRe.setStatus(status);
        appRe.setAuditOptnion(auditOptnion);
        appRe.setReviewTime(new Date());
        Permission permission= (Permission) session.getAttribute("user");
        if (Validator.isNotNullOrEmpty(permission)){
            appRe.setReviewer(permission.getUserName());
        }
         appId=masterDataService.updateExaminDataById(appRe);
        jb.put("result",appId);
        if (appId>0){
            jb.put("success",true);
        }else {
            jb.put("success",false);
        }
        return jb;
    }

}
