package com.thunisoft.controller.login;


import com.thunisoft.service.LoginService;
import com.thunisoft.util.CommonUtils;
import com.thunisoft.util.ControllerTool;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * CommonController
 * @author
 * @version 1.0
 *
 */
@Controller("myLoginController")
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
    /**
     * 获取当前登录人id，若为null则表明无法获取当前登录人，当前登录人未登录或获取时出错
     * @return
     */
    @RequestMapping("/logininfo")
    @ResponseBody
    public JSONObject getLoginMessage(){
        return CommonUtils.getJsonRes(loginService.getLoginMessage());
    }
    
    /**
     * 获取当前是否登录
     * @return
     */
    @RequestMapping("/islogin")
    @ResponseBody
    public JSONObject isLogin(){
        return CommonUtils.getJsonRes(ControllerTool.isLogin());
    }
    
    /**
     * 获取当前用户是否异地登录
     * 是否在当前用户已登陆但仍然请求登陆方法
     * @param username
     * @return
     */
    @RequestMapping(value="/iselsewhere",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject isElsewhere(String username){
        return CommonUtils.getJsonRes(loginService.isActive(username));
    }
    
    /**
     * 自定义登录接口
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/ilogin",method=RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestParam("username") String username,@RequestParam("password") String password){
        return CommonUtils.getJsonRes(loginService.login(username, password));
    }
    
    /**
     * 自定义单点登录接口
     * @return
     */
    @RequestMapping(value="/iloginsso")
    @ResponseBody
    public JSONObject loginsso(@RequestParam String zybm){
        return CommonUtils.getJsonRes(loginService.loginSSO(zybm));
    }
    
    /**
     * 伪单点登录跳转链接
     * @param role 角色名
     * @param sys 系统名
     * @return
     */
    @RequestMapping(value="/{sys}/iloginsso")
    public String singleLogin(@RequestParam("ROLE") String role, @PathVariable String sys){
        if(StringUtils.isNotBlank(role)){
            loginService.loginSSO(role);
        }
//        System.out.println(sys);
        String currentUserId = ControllerTool.getCurrentUserId();
        System.out.println(currentUserId);
        return "redirect:/ptmodule/zmq/html/index.html";
    }
    
    /**
     * 伪单点登录跳转链接
     * @param role 角色名
     * @return
     */
    @RequestMapping(value="/allloginsso",method=RequestMethod.GET)
    public String allLogin(@RequestParam("ROLE") String role,ModelMap map){
        if(StringUtils.isNotBlank(role)){
            JSONArray allLoginSSO = loginService.allLoginSSO(role);
            if (allLoginSSO!=null&&!allLoginSSO.isEmpty()) {
            	if (allLoginSSO.size()==1) {
            		JSONObject obj =(JSONObject) allLoginSSO.get(0);
            		String address = (String)obj.get("address");
					return "redirect:"+address;
				}
            	map.put("result", "success");
            	map.put("has", allLoginSSO);
			}else{
				map.put("result", "failure");
			}
        }
        return "myIndex";
    }
    
    /**
     * 获取菜单
     * @return
     */
    @RequestMapping(value="/menu")
    @ResponseBody
    public JSONArray getMenu(){
    	JSONArray menu =null;
        menu = loginService.getMenu();
        return menu;
    }
    /**
     * 获取某个菜单是否有权限
     * @return
     */
    @RequestMapping(value="/menuQX")
    @ResponseBody
    public JSONObject getSingleMenu(@RequestParam("menuName")String menuName){
    	return CommonUtils.getJsonRes(loginService.getSingleMenu(menuName));
    }

    /**
     * 获取权限
     */
    @RequestMapping(value="/getType")
    @ResponseBody
    public JSONObject getType(){
        return CommonUtils.getJsonRes(loginService.getType());
    }

    /**
     * 访问登录页面
     * @return
     */
    @RequestMapping("/goLogin")
    public Object login(){
        return "login";
    }
    /**
     * 访问登录页面
     * @return
     */
    @RequestMapping("/loginOut")
    public Object quitlogin(HttpServletRequest request, HttpServletResponse response){
         HttpSession session = request.getSession(false);
         session.removeAttribute("user");
        return "login";
    }
}
