package com.example.shiro.data;

import lombok.ToString;
import org.apache.shiro.authc.AuthenticationToken;


@ToString
public class CustomerAuthenticationTokenOne implements AuthenticationToken {

    private final String token;

    public CustomerAuthenticationTokenOne(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
