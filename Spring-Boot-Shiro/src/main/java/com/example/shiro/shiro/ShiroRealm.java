package com.example.shiro.shiro;

import com.example.shiro.data.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import java.util.*;

public class ShiroRealm extends AuthorizingRealm {

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

	/**
	 * 获取用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		String userName = user.getUsername();

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		// 获取用户角色集
		List<String> roleList = roleMap.get(userName);
		Set<String> roleSet = new HashSet<String>();
		for (String r : roleList) {
			roleSet.add(r);
		}
		simpleAuthorizationInfo.setRoles(roleSet);

		// 获取用户权限集
		List<String> permissionList = permissionMap.get(userName);
		Set<String> permissionSet = new HashSet<String>();
		for (String p : permissionList) {
			permissionSet.add(p);
		}
		simpleAuthorizationInfo.setStringPermissions(permissionSet);
		return simpleAuthorizationInfo;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		String authPassword= userMap.get(userName);
		if (StringUtils.isEmpty(authPassword)){
			throw new UnknownAccountException("用户名或密码错误！");
		}
		if (!password.equals(authPassword)) {
			throw new IncorrectCredentialsException("用户名或密码错误！");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(new User(userName,password), password, getName());
		return info;
	}

}
