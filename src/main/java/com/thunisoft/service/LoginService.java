/*
 * @(#)CommonService.java 2017年12月27日下午2:06:50
 * fzba
 * Copyright 2017 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.thunisoft.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.thunisoft.pojo.LoginBean;
import com.thunisoft.pojo.MenuBean;
import com.thunisoft.shiro.token.MyToken;
import com.thunisoft.util.ControllerTool;
import com.thunisoft.util.PropertiesUtil;
import com.thunisoft.util.RequestUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * CommonService
 * @author jiangyishan
 * @version 1.0
 *
 */
@Service("LoginService")
public class LoginService {

    private static Logger logger = LoggerFactory.getLogger(LoginService.class);
    
    /**
     * 登录session集合 
     */
    private static Map<String,HttpSession> logins = new HashMap<String,HttpSession>();
    
    /**
     * 获取登录信息
     * @return
     */
    public JSONObject getLoginMessage(){
        JSONObject loginMessage = null;
        if(ControllerTool.isLogin()){
        	LoginBean user = ControllerTool.getCurrentUser();
        	loginMessage = new JSONObject();
        	loginMessage.put("id", user.getZybm());
        	loginMessage.put("login", user.getAccount());
        	loginMessage.put("name", user.getUsername());
        }
        return loginMessage;
    }
    
    /**
     * 登录状态
     * @param username
     * @return
     */
    public boolean isActive(String username){
        HttpSession httpSession = logins.get(username);
        try {
            Object value = httpSession.getAttribute("user");
			if(httpSession!=null&&value!=null){
                return true;
            }
        } catch (Exception e) {
            logger.info(username,"已销毁session");
        }
        return false;
    }
    
    /**
     * 伪单点登录的入口
     * @param username 用户名
     */
    public void singleLogin(String username){
        if(!ControllerTool.isLogin()){
        	loginSSO(username);
        }
    }
    
    /**shiro的登录,未验证任何东西,本系统登陆成功后,调用此方法
	 * @param zybm
	 * @param loginIp
	 */
	public void loginSSOForShiro(String zybm,String loginIp) {
		Subject subject = SecurityUtils.getSubject();
		MyToken token = new MyToken(zybm, "", loginIp, "SSO");
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**shiro的登录,未验证任何东西,本系统登陆成功后,调用此方法
	 * @param loginIp
	 */
	public void loginForShiro(String username, String password,String loginIp) {
		Subject subject = SecurityUtils.getSubject();
		MyToken token = new MyToken(username, "D41D8CD98F00B204E9800998ECF8427E", loginIp, "account");
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /**
	 * 单点登录逻辑
	 *
	 * @return
	 */
	public Boolean loginSSO(String zybm) {
		String loginIP = getLoginIP();
		LoginBean user = LoginBusiness.loginByUserCode(zybm, loginIP);
		return dealSessionAndLoginForShiro(user);
	}
	/**
	 * 单点登录逻辑
	 *
	 * @return
	 */
	public JSONArray allLoginSSO(String zybm) {
		String loginIP = getLoginIP();
		JSONArray result =new JSONArray();
		LoginBean user = LoginBusiness.loginByUserCode(zybm, loginIP);
		if (user==null) {
			return null;
		}
		String allSystemPrivilege= PropertiesUtil.getProperty("all.system.privilege", "wdfh,cbpz,hshc,hssw,hydl");
		String[] systemArr =allSystemPrivilege.split(",");
		if (systemArr==null||systemArr.length==0) {
			return null;
		}
		for (String str : systemArr) {
			if(ControllerTool.hasSystemQx(user, str, false)){
				JSONObject tmp =new JSONObject();
				tmp.put("code", str);
				tmp.put("name",PropertiesUtil.getProperty(str));
				String totalAddress = PropertiesUtil.getProperty("totalAddress");
				String systemIp = PropertiesUtil.getProperty("system.ip");
				String address =systemIp+str+"/"+str+totalAddress+zybm;
				tmp.put("address",address);
				result.add(tmp);
			}
		}
		return result;
	}
	/**
	 * 登录逻辑
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean login(String username, String password) {
		String loginIP = getLoginIP();
		LoginBean user = LoginBusiness.loginByAccountAndPassword(username, password, loginIP);
		return dealSessionAndLoginForShiro(user);
	}
	/**登陆后处理session和继续登陆shiro
	 * @param user
	 * @return
	 */
	private Boolean dealSessionAndLoginForShiro(LoginBean user) {
		HttpSession session = getSession(false);
		if (user != null) {
			String zybm = user.getZybm();
			String username =user.getAccount();
			// 他处登陆挤掉
			HttpSession httpSession = logins.get(zybm);
			if (httpSession != null&&httpSession!=session) {
				try {
					httpSession.invalidate();
				} catch (Exception e) {
					logger.info(zybm, "已销毁session");
				}
			}
			session.setAttribute("user", user);
			loginForShiro(zybm, "",username);
			logins.put(zybm, session);
			return true;
		}
		return false;
	}
	
	
	/**
	 * 通过request获取 用户的ip
	 * 
	 * @return ip
	 */
	private String getLoginIP() {
		return getRequest().getRemoteAddr();
	}
	/**
	 * 通过RequestContextHolder获取request
	 * 
	 * @return request
	 */
	private HttpServletRequest getRequest() {
		return RequestUtil.getRequest();
	}
	/**
	 * 通过request获取session
	 * 
	 * @return session
	 */
	private HttpSession getSession(boolean flag) {
		return getRequest().getSession(flag);
	}

	public JSONArray getMenu() {
		JSONArray result =new JSONArray();
		JSONObject single = null;
		List<MenuBean> currentUserMenu = ControllerTool.getCurrentUserMenu();
		if (currentUserMenu==null) {
			single=new JSONObject();
			result.add(single);
			return null;
		}
		for (MenuBean menuBean : currentUserMenu) {
			single=new JSONObject();
			single.put("name",menuBean.getMenuName());
			single.put("url",menuBean.getMenuAddress());
			result.add(single);
		}
        return result;
	}
	public Boolean getSingleMenu(String menuStr) {
		List<MenuBean> currentUserMenu = ControllerTool.getCurrentUserMenu();
		if (currentUserMenu==null) {
			return false;
		}
		Boolean menuQX = ControllerTool.getMenuQXCF(currentUserMenu, menuStr);
        return menuQX;
	}
	
}
