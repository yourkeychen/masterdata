package com.thunisoft.service;

import com.thunisoft.pojo.LoginBean;
import com.thunisoft.pojo.MenuBean;
import com.thunisoft.pojo.RoleBean;
import com.thunisoft.util.JurisdictionUtil;
import com.thunisoft.util.LoginUtil;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.collections.CollectionUtils;
import org.dom4j.*;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class LoginBusiness {

	/**
	 * 账户密码登陆
	 * 
	 * @param account
	 *            账号
	 * @param passWord
	 *            密码
	 * @param loginIP
	 *            登陆处IP
	 * @return 用户对象
	 */
	public static LoginBean loginByAccountAndPassword(String account,
			String passWord, String loginIP) {
		LoginBean str2LoginBean = null;
		String requestXml = LoginUtil.createRequestXMLForLogin(
				JurisdictionUtil.get("systemCode"), account, passWord, loginIP);
		String resultXml = sendXml(requestXml);
		resultXml = tran(resultXml);
		System.out.println(resultXml);
		if (!resultXml.equals("")) {
			str2LoginBean = str2LoginBean(resultXml);
		}
		return str2LoginBean;
	}

	/**
	 * 使用职员编码的免密登陆
	 * 
	 * @param userCode
	 *            职员编码
	 * @param loginIP
	 *            登陆处ip
	 * @return 用户对象
	 */
	public static LoginBean loginByUserCode(String userCode, String loginIP) {
		LoginBean str2LoginBean = null;
		String requestXml = LoginUtil.createRequestXMLForLogin(
				JurisdictionUtil.get("systemCode"), userCode, loginIP);
		String resultXml = sendXml(requestXml);
		resultXml = tran(resultXml);
		System.out.println(resultXml);
		if (!resultXml.equals("")) {
			str2LoginBean = str2LoginBean(resultXml);
		}
		return str2LoginBean;
	}

	/**
	 * 权限系统返回的 xml 字符串 有success|或者error|的前缀,去掉后才能解析为xml
	 * 
	 * @param str
	 *            权限系统返回的 xml 字符串
	 * @return 去掉success|或者error|的前缀 的结果
	 */
	public static String tran(String str) {
		if (str != null && str.startsWith("success|")) {
			return str.replace("success|", "");
		} else {
			return "";
		}
	}

	/**
	 * xml字符串转化为菜单对象
	 * 
	 * @param str
	 * @return
	 */
	public static List<MenuBean> str2MenuBean(String str) {
		List<MenuBean> result = null;

		try {
//			System.out.println(str);
			Document doc = DocumentHelper.parseText(str);
			Element rootElement = doc.getRootElement();
			List<Element> elements = rootElement.elements();
			result = createChilrenMenu(elements);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据xml元素创建菜单对象
	 * 
	 * @param element
	 * @return
	 */
	private static MenuBean createMenu(Element element) {
		MenuBean menu = null;
		if (null != element) {
			List<Element> elements = element.elements();
			menu = new MenuBean();
			if (CollectionUtils.isNotEmpty(elements)) {
				List<MenuBean> chilren = createChilrenMenu(elements);
				menu.setMenus(chilren);
			}
			String menuCode = element.attributeValue("id");
			String menuName = element.attributeValue("name");
			String menuParam = element.attributeValue("para");
			String menuAddress = element.attributeValue("url");
			menu.setMenuCode(menuCode);
			menu.setMenuName(menuName);
			menu.setMenuParam(menuParam);
			menu.setMenuAddress(menuAddress);
		}
		return menu;
	}

	/**创建子菜单
	 * @param elements
	 * @return
	 */
	private static List<MenuBean>  createChilrenMenu(List<Element> elements) {
		List<MenuBean> chilrenMenu =null;
		if (CollectionUtils.isNotEmpty(elements)) {
			chilrenMenu =new ArrayList<MenuBean>();
			for (int i = 0; i < elements.size(); i++) {
				Element childElement = elements.get(i);
				MenuBean childMenu = createMenu(childElement);
				childMenu.setMenuOrder(i);
				chilrenMenu.add(childMenu);
			}
		}
		return chilrenMenu;
	}

	/**
	 * xml字符串转化为用户对象
	 * 
	 * @param str
	 *            xml字符串
	 * @return 用户对象
	 */
	public static LoginBean str2LoginBean(String str) {
		LoginBean user = null;
		try {
			user = new LoginBean();
			Document doc = DocumentHelper.parseText(str);
			Element rootElement = doc.getRootElement();

			Element element = rootElement.element("USER");

			Attribute roleNames = element.attribute("RoleName");
			Attribute roleType = element.attribute("RoleType");
			Attribute roleCode = element.attribute("UserRole");

			List<RoleBean> str2Role = str2Role(roleCode, roleType, roleNames);
			if (str2Role==null) {
				return null;
			}
			user.setRole(str2Role);

			Attribute zybm = element.attribute("EmployeeNo");
			Attribute pw = element.attribute("Password");

			Attribute xtdm = element.attribute("SystemCode");
			Attribute account = element.attribute("UserCode");
			Attribute bmbm = element.attribute("UserDeptCode");
			Attribute bmmc = element.attribute("UserDeptName");
			// element.attribute("UserID");
			Attribute menuXmlStr = element.attribute("UserMenu");
			Attribute username = element.attribute("UserName");
			Attribute fybm = element.attribute("UserOrgCode");
			Attribute fymc = element.attribute("UserOrgName");
			// Attribute zzdm = element.attribute("ZZDM");

			user.setZybm(getAttrStr(zybm));
			user.setPw(getAttrStr(pw));
			user.setXtdm(getAttrStr(xtdm));
			user.setAccount(getAttrStr(account));
			user.setBmdm(getAttrStr(bmbm));
			user.setBmmc(getAttrStr(bmmc));
			user.setMenuXmlStr(getAttrStr(menuXmlStr));
			user.setUsername(getAttrStr(username));
			user.setFydm(getAttrStr(fybm));
			user.setFymc(getAttrStr(fymc));
			user.setMenu(str2MenuBean(user.getMenuXmlStr()));
			// user.setBmmc(getAttrStr(bmmc));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return user;
	}

	private static String getAttrStr(Attribute attr) {
		return attr == null ? "" : attr.getText();
	}

	/**
	 * 将dom的属性字符串转化为角色对象
	 * 
	 * @param roleCode
	 *            dom的attribute :角色代码
	 * @param roleType
	 *            dom的attribute :角色类型
	 * @param roleNames
	 *            dom的attribute :角色名称
	 * @return 角色对象的 集合
	 */
	private static List<RoleBean> str2Role(Attribute roleCode,
			Attribute roleType, Attribute roleNames) {
		List<RoleBean> result =null;
		if (roleCode != null && roleType != null && roleNames != null) {
			String codeText = roleCode.getText();
			String typeText = roleType.getText();
			String nameText = roleNames.getText();
			if (codeText.trim().equals("")) {
				return null;
			}
			String[] codeTexts = codeText.split(",");
			String[] typeTexts = typeText.split(",");
			String[] nameTexts = nameText.split(",");
			result = new ArrayList<RoleBean>();
			for (int i = 0; i < codeTexts.length; i++) {
				RoleBean bean = new RoleBean();
				bean.setRoleCode(codeTexts[i]);
				bean.setRoleName(nameTexts[i]);
				bean.setRoleType(typeTexts[i]);
				result.add(bean);
			}
		}
		return result;
	}

	/**
	 * 向权限系统发送字符串来验证是否有权限
	 * 
	 * @param sendxml
	 *            请求权限系统使用的xml字符串
	 * @return 权限系统返回的结果字符串 xml格式
	 */
	private static String sendXml(String sendxml) {
		String result = "";
		try {
			String path = JurisdictionUtil.get("jurisdictionBaseAddress");
			String endpoint = path + JurisdictionUtil.get("jurisdictonService");
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.setOperationName(new QName("RemoteDBServiceService", "login"));
			result = (String) call.invoke(new Object[] { sendxml });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
