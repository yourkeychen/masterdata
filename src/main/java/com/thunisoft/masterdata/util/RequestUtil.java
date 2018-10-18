package com.thunisoft.masterdata.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestUtil {
	/**获取当前请求
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return sra.getRequest();
	}
	/**
	 * 通过request获取session
	 *  
	 * @return session
	 */
	public static HttpSession getSession(){
		return getSession(false);
	}
	
	/**通过request获取session
	 * @param flag
	 * @return
	 */
	public static HttpSession getSession(boolean flag){
		return getRequest().getSession(flag);
	}
}
