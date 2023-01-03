package com.example.shiro.shiro;

import com.example.shiro.data.CustomerAuthenticationTokenTwo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
@Slf4j
public class RealmTepyTwo extends AuthorizingRealm {

	private static final Map<String,String> userMap=new HashMap<>();
	private static final Map<String, List<String>> roleMap=new HashMap<>();
	private static final Map<String, List<String>> permissionMap=new HashMap<>();

	static {
		userMap.put("user","1234");
		userMap.put("user1","1234");

		roleMap.put("user",Arrays.asList("admin", "user"));
		roleMap.put("user1",Arrays.asList( "user"));

		permissionMap.put("user",Arrays.asList("admin", "user"));
		permissionMap.put("user1",Arrays.asList( "user"));
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		return null;
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof CustomerAuthenticationTokenTwo;
	}
}
