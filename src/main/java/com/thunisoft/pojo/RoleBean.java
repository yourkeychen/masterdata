package com.thunisoft.pojo;


public class RoleBean {
	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色代码
	 */
	private String roleCode;
	
	/**角色类型 
	 * 3 业务管理员
	 * 4 业务角色
	 */
	private String roleType;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	@Override
	public String toString() {
		return " RoleBean "+super.toString()+"[roleName=" + roleName + ", roleCode=" + roleCode
				+ ", roleType=" + roleType + "]";
	}
	
	
	
	
	
	
}
