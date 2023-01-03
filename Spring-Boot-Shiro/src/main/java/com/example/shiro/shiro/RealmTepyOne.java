package com.example.shiro.shiro;


import com.example.shiro.data.CustomerAuthenticationTokenOne;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RealmTepyOne extends AuthorizingRealm {


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if(principals!=null && principals.getPrimaryPrincipal() instanceof AuthorizationInfo) {
            return (AuthorizationInfo) principals.getPrimaryPrincipal();
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
      return null;
    }


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomerAuthenticationTokenOne;
    }
}
