package com.it.service;

/**
 * Created by xieyue on 2016/7/8.
 */


import com.it.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ShiroRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    @Inject
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        if (user != null) {
            //将用户的角色名称赋值给info
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole(user.getRole().getRolename());

            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername(); //获取用户表单中的账号
        User user = userService.findUserByUsername(username); //根据账号查找对应的对象
        if (user != null) {
            if (!user.getEnable()) {
                throw new LockedAccountException("账号已被禁用");
            }
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        } else {
            throw new UnknownAccountException("账号或密码错误");
        }
    }
}
