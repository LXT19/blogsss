package com.blog.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager securityManager){

        //创建shiroFilterFactoryBean对象
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(securityManager);

        //添加shiro内置过滤器
        /**
         * anon:无需认证就可以访问
         * author:必须认证才可以访问
         * user:必须拥有记住我
         * perms:拥有某个资源权限才能使用
         * role:拥有某个角色才能访问
         */

        //拦截
        Map<String,String> filterMap=new LinkedHashMap<>();

        //设置拦截页面即权限
        filterMap.put("/admin/login","anon");
        filterMap.put("/admin/*","authc");

        //将拦截设置加入
        bean.setFilterChainDefinitionMap(filterMap);

        //设置登陆请求
        bean.setLoginUrl("/admin");

        System.out.println("执行前往登陆页面");

        return bean;

    }

    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //创建Realm对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiroDialect：用来整合thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
