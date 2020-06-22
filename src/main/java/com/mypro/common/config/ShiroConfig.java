package com.mypro.common.config;

import com.mypro.modules.auth.shiro.JwtFilter;
import com.mypro.modules.auth.shiro.ShiroRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ShiroConfig {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager manager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        Map<String,String> map = new LinkedHashMap<>();
        map.put("/noauth/**","anon");
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt",new JwtFilter());
        bean.setFilters(filterMap);
        bean.setUnauthorizedUrl("/sys/403");//没有获取许可的跳转
        bean.setLoginUrl("/sys/403");//没有登陆的跳转
        //除了以上链接，全部需要jwtfilter 判断是否拥有权限
        map.put("**","jwt");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager getSecurityManager(ShiroRealm shiroRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroRealm);

        return manager;
    }
}
