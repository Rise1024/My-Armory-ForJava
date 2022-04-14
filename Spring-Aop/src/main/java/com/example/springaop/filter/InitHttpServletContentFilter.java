package com.example.springaop.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author zds
 * @Description
 * @createTime 2021/10/21 15:44
 */

@Component
public class InitHttpServletContentFilter implements Filter {


    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        HttpServletContent.setRequest((HttpServletRequest) arg0);
        HttpServletContent.setResponse((HttpServletResponse) arg1);
        arg2.doFilter(arg0, arg1);
    }


    @Override
    public void destroy() {
    }

}
