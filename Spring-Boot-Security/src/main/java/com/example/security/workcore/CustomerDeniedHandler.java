package com.example.security.workcore;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

/**
 * @Author: DS
 * @Date: 2023/11/20 14:00
 * @Description:
 **/
public class CustomerDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(SC_FORBIDDEN);
        PrintWriter out = response.getWriter();
        out.write("没有相关权限");
        out.flush();
        out.close();
    }
}
