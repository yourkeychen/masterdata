package com.thunisoft.masterdata.util;

public class LoginUtil {
	/**
	 * 登陆:账号密码登陆: 拼装请求xml
	 * 
	 * @param systemCode
	 *            权限系统中 系统代码
	 * @param userName
	 *            用户账号
	 * @param passWord
	 *            用户密码
	 * @param loginIP
	 *            登录端ip
	 * @return 拼接好的请求用xml字符串
	 */
	public static String createRequestXMLForLogin(String systemCode,
			String userName, String passWord, String loginIP) {
		StringBuilder result = new StringBuilder();
		result.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
		result.append("<ROOT ACTION=\"multiRoleLogin\">");
		result.append("<COMMAND><![CDATA[");
		result.append(systemCode);
		result.append("|");
		result.append(userName);
		result.append("|");
		result.append(passWord);
		result.append("|");
		result.append(loginIP);
		result.append("]]></COMMAND>");
		result.append("</ROOT>");
		return result.toString();
	}

	/**
	 * 登陆:职员编码免密登陆: 拼装请求xml
	 * 
	 * @param systemCode
	 *            权限系统中 系统代码
	 * @param userCode
	 *            职员编码
	 * @param loginIP
	 *            登录端ip
	 * @return 拼接好的请求用xml字符串
	 */
	public static String createRequestXMLForLogin(String systemCode,
			String userCode, String loginIP) {
		StringBuilder result = new StringBuilder();
		result.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
		result.append("<ROOT ACTION=\"multiLoginNoPass\">");
		result.append("<COMMAND><![CDATA[");
		result.append(systemCode);
		result.append("|");
		result.append(userCode);
		result.append("|");
		result.append(loginIP);
		result.append("]]></COMMAND>");
		result.append("</ROOT>");
		return result.toString();
	}

	/**
	 * 获取明细:根据职员编码和菜单代码获取改职员位于该菜单上的权限明细 : 拼装请求xml
	 * 
	 * @param systemCode
	 *            权限系统中 系统代码
	 * @param userCode
	 *            职员编码
	 * @param menuCode
	 *            菜单代码
	 * @param loginIP
	 *            登录端ip
	 * @return 拼接好的请求用xml字符串
	 */
	public static String createRequestXMLForMX(String systemCode,
			String userCode, String menuCode, String loginIP) {
		StringBuilder result = new StringBuilder();
		result.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
		result.append("<ROOT ACTION=\"multiAuthorityByCDDM\">");
		result.append("<COMMAND><![CDATA[");
		result.append(systemCode);
		result.append("|");
		result.append(userCode);
		result.append("|");
		result.append(menuCode);
		result.append("|");
		result.append(loginIP);
		result.append("]]></COMMAND>");
		result.append("</ROOT>");
		return result.toString();
	}

}
