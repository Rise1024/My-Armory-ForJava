package com.example.shiro.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zds
 * @Description
 * @createTime 2022/8/10 15:04
 */

@Component
@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    private static final List<String> roleList=new ArrayList<>();
    private static final List<String> permissionList=new ArrayList<>();

    private static final String username="test";
    private static final String password="test";


    static {
        roleList.add("admin");
        permissionList.add("*");
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        System.out.println("调用授权验证："+primaryPrincipal);
        if (!CollectionUtils.isEmpty(roleList)) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            for (String role : roleList) {
                simpleAuthorizationInfo.addRole(role);
                //权限信息

                if(!CollectionUtils.isEmpty(permissionList)){
                    for (String permission : permissionList) {
                        simpleAuthorizationInfo.addStringPermission(permission);
                    }
                }
            }
            return simpleAuthorizationInfo;
        }

        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
            return new SimpleAuthenticationInfo(username, password, this.getName());
    }
}

