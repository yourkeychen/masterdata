package com.thunisoft.masterdata.shiro.realm;

import com.thunisoft.masterdata.pojo.LoginBean;
import com.thunisoft.masterdata.util.ControllerTool;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {


    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principalcollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }
    /**
     * 认证回调函数、登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationtoken)
            throws AuthenticationException {
        LoginBean user = ControllerTool.getCurrentUser();
        return new SimpleAuthenticationInfo(user, "D41D8CD98F00B204E9800998ECF8427E", "sf");
    }
}
