package com.thunisoft.masterdata.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MyToken extends UsernamePasswordToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loginType;
	private String loginIp;
	private String name;
	
	public MyToken(String username,String password, String loginIp, String loginType) {
		super(username,password);
		this.loginType = loginType;
		this.loginIp = loginIp;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
