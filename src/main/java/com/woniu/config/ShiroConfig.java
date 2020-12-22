package com.woniu.config;
import com.woniu.component.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    /**
     *shiroFilter,该对象拦截请求后需要调用securityManager进行认证和授权,需要注入securityManager(通过参数注入)
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //过滤器的规则配置
//        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/js/**","anon");
//        filterChainDefinitionMap.put("/**","user");
//        filterChainDefinitionMap.put("/login.html","anon");
//        filterChainDefinitionMap.put("/register.html","anon");
//        filterChainDefinitionMap.put("/user","anon");


//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        shiroFilterFactoryBean.setLoginUrl("/login.html");

        return shiroFilterFactoryBean;
    }

    /**
     * securityManager:
     * 该对象在进行认证和授权时需要的数据有realm提供,需要注入realm(通过参数注入)
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm((realm));
        return defaultWebSecurityManager;
    }

    /**
     * realm 该对象用于获取认证和授权需要的数据,一般存数据库中获取
     */
    @Bean
    public Realm realm(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        CustomerRealm customerRealm = new CustomerRealm();
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);
        return  customerRealm;
    }
}
