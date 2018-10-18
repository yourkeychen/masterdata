package com.thunisoft.shiro.filter;


import com.thunisoft.util.ControllerTool;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class MyAuthenticationFilter extends PathMatchingFilter {
	private void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		WebUtils.issueRedirect(request, response, "/login"); 
    }
	@SuppressWarnings("unused")
	private void redirectToIndex(ServletRequest request, ServletResponse response) throws IOException {
		WebUtils.issueRedirect(request, response, "/index"); 
    }
	
	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		if(! ControllerTool.isLogin()){
			redirectToLogin(request, response);
			return false;
		}
		return true;
	}
	
	
	
}
