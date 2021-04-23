package com.blog.config;

import com.blog.bean.User;
import com.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义userRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //SimpleAuthorizationInfo为用户授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        //拿到当前登录对象
        Subject subject = SecurityUtils.getSubject();

        //拿到user对象
        User user= (User) subject.getPrincipal();

        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取token中的用户名和密码对象
        UsernamePasswordToken userToken= (UsernamePasswordToken) token;

        User user=userService.checkUserByName(userToken.getUsername());

      //  System.out.println("获取到用户");


        if(user==null)
            return null;

      //  System.out.println("执行认证，用户存在");

        //密码认证，shiro做
        return new SimpleAuthenticationInfo(user,user.getPassword(),user.getUsername());

    }
}
