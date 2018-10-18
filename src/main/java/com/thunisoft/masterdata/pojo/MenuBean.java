package com.thunisoft.masterdata.pojo;

import java.util.List;

public class MenuBean {
	/**
	 * 菜单编码
	 */
	private String menuCode;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 配置的菜单参数
	 */
	private String menuParam;
	/**
	 * 配置的菜单url
	 */
	private String menuAddress;
	/**
	 * 同一个父菜单下的排序
	 */
	private int menuOrder;
	/**
	 * 子菜单
	 */
	private List<MenuBean> menus;
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuAddress() {
		return menuAddress;
	}
	public void setMenuAddress(String menuAddress) {
		this.menuAddress = menuAddress;
	}
	public int getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getMenuParam() {
		return menuParam;
	}
	public void setMenuParam(String menuParam) {
		this.menuParam = menuParam;
	}
	public List<MenuBean> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuBean> menus) {
		this.menus = menus;
	}
	@Override
	public String toString() {
		return super.toString()+" [menuCode=" + menuCode + ", menuName=" + menuName
				+ ", menuParam=" + menuParam + ", menuAddress=" + menuAddress
				+ ", menuOrder=" + menuOrder + ", menus=" + menus + "]";
	}
}
