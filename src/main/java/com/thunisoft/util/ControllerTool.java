package com.thunisoft.util;


import com.alibaba.druid.util.StringUtils;
import com.thunisoft.pojo.LoginBean;
import com.thunisoft.pojo.MenuBean;
import com.thunisoft.pojo.Permission;
import org.apache.commons.collections.CollectionUtils;


import javax.servlet.http.HttpSession;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControllerTool {
	
	
	public static boolean isLogin() {
//		LoginBean currentUser = getCurrentUser();
		Permission currentUser = getCurrentUser2();
		if (currentUser!=null) {
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * 获取登陆人sessionBean
	 * @return
	 */
	public static LoginBean getCurrentUser() {
		HttpSession session = RequestUtil.getSession(false);
		if (session==null) {
			return null;
		}
		LoginBean currentUser = (LoginBean) session.getAttribute("user");
		return currentUser;
	}

	public static Permission getCurrentUser2() {
		HttpSession session = RequestUtil.getSession(false);
		if(session == null){
			return null;
		}
		Permission currentUser = (Permission) session.getAttribute("user");
		return currentUser;
	}

	/**
	 * 获取登录人sessionBean
	 */

	
	/**
	 * 获取登陆人的职员编码
	 * @return
	 */
	public static String getCurrentUserId() {
		LoginBean currentUser = ControllerTool.getCurrentUser();
		if (currentUser!=null) {
			return currentUser.getZybm();
		}
		return null;
	}
	
	/**获取登陆人的在权限系统中配置的菜单
	 * @return 
	 */
	public static List<MenuBean> getCurrentUserMenu() {
		LoginBean currentUser = ControllerTool.getCurrentUser();
		if (currentUser!=null) {
			return currentUser.getMenu();
		}
		return null;
	}


	
	 /**获取登陆人的在单个菜单是否有权限 系统拆分前的方法
	 * @param currentUserMenu 菜单list
	 * @param menuName 需判断的菜单名称
	 * @param flag 是否递归遍历所有子菜单 false只判断1级菜单
	 * @return
	 */
	public static Boolean getMenuQX(List<MenuBean> currentUserMenu,String menuName,boolean flag) {
		Boolean menuQX =false;
		if (CollectionUtils.isEmpty(currentUserMenu)|| StringUtils.isEmpty(menuName)) {
			return false;
		}
		for (MenuBean menuBean : currentUserMenu) {
			if (menuName.equals(menuBean.getMenuName())) {
				return true;
			}
			if (flag) {
				List<MenuBean> menus = menuBean.getMenus();
				if (CollectionUtils.isNotEmpty(menus)) {
					menuQX= getMenuQX(menus, menuName,flag);
				}
				if (true==menuQX) {
					return menuQX;
				}
			}
		}
		return menuQX;
	}



	/**登陆时使用,判断user是否有本某个子系统的权限
	 * @param user 用户对象
	 * @param systemPrivilegeCode 需要判断的子系统代码
	 * @param flag 是否递归遍历所有菜单,true是,false 只判断下探到1级菜单权限
	 * @return
	 */
	public static Boolean hasSystemQx(LoginBean user,
			String systemPrivilegeCode,boolean flag) {
		if (user==null) {
			return false;
		}
		List<MenuBean> currentUserMenu = user.getMenu();
		Boolean menuQX =false;
		menuQX = getMenuQX(currentUserMenu, systemPrivilegeCode, flag);
		return menuQX;
	}
	
	
	/**获取登陆人的在单个菜单是否有权限 ,系统拆分后的方法
	 * @return 
	 */
	public static Boolean getMenuQXCF(List<MenuBean> currentUserMenu,String menuName) {
		String systemPrivilegeCode = PropertiesUtil.getProperty("system.privilege", "");
		Boolean menuQX =false;
		if (CollectionUtils.isEmpty(currentUserMenu)||StringUtils.isEmpty(menuName)) {
			return false;
		}
		//先获取对应系统的子菜单
		MenuBean menuBeanByName = getMenuBeanByName(currentUserMenu,systemPrivilegeCode);  
		if (menuBeanByName==null) {
			return false;
		}
		ArrayList<MenuBean> currentUserMenu2 = new ArrayList<MenuBean>();
		currentUserMenu2.add(menuBeanByName);
		menuQX = getMenuQX(currentUserMenu2, menuName,true);
		return menuQX;
	}
	/**获取登陆人的在单个菜单是否有权限
	 * @return 
	 */
	/**从菜单对象集合中 通过 菜单名称 获取指定的菜单对象(集合的元素或者元素的子元素)
	 * @param currentUserMenu 菜单对象集合
	 * @param menuName 目标菜单的菜单名称 
	 * @return 指定的菜单对象
	 */
	public static MenuBean getMenuBeanByName(List<MenuBean> currentUserMenu,String menuName) {
//		String systemPrivilegeCode = ArteryConfigUtil.getProperty("system.privilege", "");
		MenuBean resultMenuBean =null;
		if (CollectionUtils.isEmpty(currentUserMenu)||StringUtils.isEmpty(menuName)) {
			return null;
		}
		for (MenuBean menuBean : currentUserMenu) {
			if (menuName.equals(menuBean.getMenuName())) {
				return menuBean;
			}
			List<MenuBean> menus = menuBean.getMenus();
			if (CollectionUtils.isNotEmpty(menus)) {
				resultMenuBean= getMenuBeanByName(menus, menuName);
			}
			if (null!=resultMenuBean) {
				return resultMenuBean;
			}
		}
		return resultMenuBean;
	}
	/**
	 * 将一个map集合转化成JavaBean
	 * @param type
	 * @param map
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	public static Object converMap(Class type,Map map) 
	    		throws IntrospectionException, IllegalAccessException,
	    		InstantiationException,InvocationTargetException{
	    	BeanInfo beanInfo = Introspector.getBeanInfo(type);
	    	Object obj = type.newInstance();
	    	
	    	PropertyDescriptor [] propertyDescriptors = beanInfo.getPropertyDescriptors();
	    	for (int i = 0; i< propertyDescriptors.length; i++){
	    		PropertyDescriptor descriptor = propertyDescriptors[i];
	    		String propertyName = descriptor.getName();
	    		if(map.containsKey(propertyName)){
	    			Object value = map.get(propertyName);
	    			
	    			Object[] args = new Object[1];
	    			
	    			descriptor.getWriteMethod().invoke(obj, args);
	    		}
	    	}
			return obj;
	}
	
//	/**根据职员编码查找部门
//	 * @param zybm
//	 * @return
//	 */
//	public static String getCurrentDept(String zybm) {
//		String dsName = "dataSource";
//		DataSourceInfo dsi = ArteryDataSourceUtil.getDataSourceService().getDataSourceById(dsName);
//		DataSource dataSource = dsi.getDataSource();
//		JdbcTemplate jdbcTemplate =new JdbcTemplate(dataSource);
//		String sql ="SELECT b.c_name FROM \"public\".t_aty_user A, \"public\".t_aty_dept b " +
//				"WHERE A .c_dept = b.c_id AND A .c_id = ? LIMIT 1 OFFSET 0";
//		String queryForObject = jdbcTemplate.queryForObject(sql, new Object[]{zybm}, String.class);
//		return queryForObject;
//	}
	
}
