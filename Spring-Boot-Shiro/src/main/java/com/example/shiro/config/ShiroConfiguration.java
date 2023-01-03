package com.example.shiro.config;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ShiroConfiguration {

    @Bean
    public SessionsSecurityManager securityManager(List<Realm> realms, PermissionResolver permissionResolver) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRememberMeManager(null);
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        securityManager.setAuthenticator(authenticator);
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setPermissionResolver(permissionResolver);
        securityManager.setAuthorizer(authorizer);
        securityManager.setRealms(realms);
        securityManager.setSessionManager(sessionManager());

        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }

    protected SessionManager sessionManager() {
        DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();
        webSessionManager.setSessionIdCookieEnabled(false);
        webSessionManager.setSessionIdUrlRewritingEnabled(false);
        webSessionManager.setSessionValidationSchedulerEnabled(true);
        webSessionManager.setDeleteInvalidSessions(true);
        webSessionManager.setGlobalSessionTimeout(600000);
        webSessionManager.setSessionValidationInterval(600000);

        return webSessionManager;
    }


    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


}
