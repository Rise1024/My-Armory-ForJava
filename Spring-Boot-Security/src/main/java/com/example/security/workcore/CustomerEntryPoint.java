package com.example.security.workcore;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 * @Author: DS
 * @Date: 2023/11/20 14:00
 * @Description:
 **/
@Slf4j
public class CustomerEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        log.error(e.getMessage());
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        out.write("没有登录");
        out.flush();
        out.close();
    }
}
