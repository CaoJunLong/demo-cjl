/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: MyRealm
 * Author:   zyl
 * Date:     2018/12/26 13:56
 * Description: test
 * History:
 */
package com.jk.shiro;


import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.User;
import com.jk.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


public class MyRealm extends AuthorizingRealm {

    @Reference(version = "1.0")
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取主身份信息
       // User userCode = (User)principals.getPrimaryPrincipal();

        //根据身份信息获取权限信息
        //模拟从数据库获取到数据
        List<String> permissions = new ArrayList<String>();
        permissions.add("user:create"); //用户的创建权限
        permissions.add("user:update"); //商品的添加权限
        permissions.add("user:delete"); //商品的添加权限
        //permissions.add("user:toAdd");
        //将查询到授权信息填充到对象中
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.从token取出用户身份信息
        String userName = (String)token.getPrincipal();
        //查询数据库 判断当前user是否为空
       User user = userService.queryUserByName(userName);
        //如果查询不到则返回null  user==null
        if(!userName.equals(user.getUserName())){//这里模拟查询不到
            throw new UnknownAccountException();
        }
        //2.根据用户userCode查询数据库

        //模拟从数据库查询到的密码
        String passWord = user.getPassWord();

        //查询不到返回null

        //查询到返回认证信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName,passWord,this.getName());
        SecurityUtils.getSubject().getSession().setAttribute("login", userName);
        return authenticationInfo;
    }

}