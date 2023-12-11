package com.example.security.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @Author: DS
 * @Date: 2023/11/22 10:00
 * @Description:
 **/
@Data
@Accessors(chain = true)
public class LoginResponse {
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录认证token
     */
    private String token;
    /**
     * 当前用户的权限资源id集合
     */
    private Set<Long> resourceIds;
}
