package com.example.shiro.filter;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.EnumSet;

/**
 * 当用户前台登录发起请求时：
 * 1.从shiro中获取subject主体
 * SecurityUtils.getSubject();
 * 2.判断当前用户是否认证过了，如果认证过了就放行了
 * subject.isAuthenticated()
 * 3.如果没有认证过，就把前台传递的账号密码封装为一个AuthenticationToken类型对象
 * 4.把UserNamePasswordToken对象传入，进行登录操作
 * subject.login(usernamepasswordtoken);
 * 5.我们配置的安全管理器中实现了doGetAuthenticationInfo方法，从数据库查询用户数据，加密加盐后进行shiro的认证
 * 6.如果认证成功，进行权限赋于。
 *
 * 实际开发中，使用shiro来控制权限,一般有三个基本对象:
 * 1、 权限对象Persimmsion
 *
 * @Data
 * @AllArgsConstructor
 * @NoArgsConstructor
 * public class Permission{
 *     private String id;
 *     private String name;
 *     private String url;
 * }

 * 2、 角色对象Role
 *
 * @Data
 * @AllArgsConstructor
 * @NoArgsConstructor
 * public class Role{
 *     private String id;
 *     private String name;
 *     //权限集合
 *     private List<Permission> permissions;
 * }

 * 3、 用户对象User
 *
 * @Data
 * @AllArgsConstructor
 * @NoArgsConstructor
 * public class User{
 *     private String id;
 *     private String username;
 *     private String password;
 *     //定义角色
 *     List<Role> roleList;
 * }
 */

@Slf4j
public class SecurityInterceptor extends HandlerInterceptorAdapter {


    private static final EnumSet<HttpMethod> ACCESS_CONTROL_METHOD = EnumSet.of(HttpMethod.POST, HttpMethod.DELETE, HttpMethod.GET, HttpMethod.PUT);

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {

        AuthenticationToken token=new AuthenticationToken() {
            @Override
            public Object getPrincipal() {
                return request.getHeader("user");
            }

            @Override
            public Object getCredentials() {
                return request.getHeader("password");
            }
        };
        Subject subject = new Subject.Builder().buildSubject();
        ThreadContext.bind(subject);

        if (subject.isAuthenticated()){

        }
        try {
            subject.login(token);
        } catch (AuthenticationException ae) {
        }

        if (shouldCheckAccessControl(request)) {
            checkAccessControl(request);
        }
        return true;
    }


    @SneakyThrows
    private void checkAccessControl(HttpServletRequest request) {
        String permission = resolvePermission(request);
        log.info("checking permission : {}", permission);

        boolean isPermitted = SecurityUtils.getSubject().isPermitted(permission);
        if (!isPermitted) {
            throw new Exception("checking permission error");
        }
    }

    private boolean shouldCheckAccessControl(HttpServletRequest request) {
        String httpMethod = request.getMethod();
        HttpMethod method = HttpMethod.resolve(httpMethod);

        return ACCESS_CONTROL_METHOD.contains(method);
    }

    private String resolvePermission(HttpServletRequest request) {
        return request.getMethod().toLowerCase() + ":" + request.getRequestURI();
    }

}
