package com.thunisoft.pojo;

import org.springframework.stereotype.Component;

import java.util.List;


@Component("loginBean")
public class LoginBean {  
	private String zybm = "";/* 登陆人职员编码 */
	 
	private String fydm = "";// 登陆者所在法院代码 
	
	private String pw ="";
	
	private String bmdm = "";// 登陆者所在部门代码 

	private String fymc = "";// 登陆者所在法院名称   

	private String bmmc = "";// 登陆者所在部门名称
 
	private String account = "";// 登陆者id  
 
//	private String showMenu = "";// 菜单 

	private int sjqx = 1;// 登陆者数据权限 默认本院

	private String username = "";// 登陆者姓名

	private String xtdm = "";

	private List menu = null;
	private String menuXmlStr = ""; // 菜单 
	
	private List<RoleBean> role;
	
	public void setZybm(String zybm) { 
		this.zybm = zybm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public void setFymc(String fymc) {
		this.fymc = fymc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public void setSjqx(int sjqx) {
		this.sjqx = sjqx;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZybm() {
		return zybm;
	}

	public String getFydm() {
		return fydm;
	}

	public String getBmdm() {
		return bmdm;
	}

	public String getFymc() {
		return fymc;
	}

	public String getBmmc() {
		return bmmc;
	}

	public int getSjqx() {
		return sjqx;
	}

	public String getUsername() {
		return username;
	}

	public String getXtdm() {
		return xtdm;
	}

	public void setXtdm(String xtdm) {
		this.xtdm = xtdm;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

//	public String getShowMenu() {
//		return showMenu;
//	}
//
//	public void setShowMenu(String showMenu) {
//		this.showMenu = showMenu;
//	}

	public List getMenu() {
		return menu;
	}

	public void setMenu(List menu) {
		this.menu = menu;
	}
	public String getMenuXmlStr() {
		return menuXmlStr;
	}
	public void setMenuXmlStr(String menuXmlStr) {
		this.menuXmlStr = menuXmlStr;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public List<RoleBean> getRole() {
		return role;
	}

	public void setRole(List<RoleBean> role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "LoginBean [zybm=" + zybm + ", fydm=" + fydm + ", pw=" + pw
				+ ", bmdm=" + bmdm + ", fymc=" + fymc + ", bmmc=" + bmmc
				+ ", loginId=" + account + ", sjqx=" + sjqx + ", username="
				+ username + ", xtdm=" + xtdm + ", menu=" + menu
				+ ", menuXmlStr=" + menuXmlStr + ", role=" + role + "]";
	}
}
