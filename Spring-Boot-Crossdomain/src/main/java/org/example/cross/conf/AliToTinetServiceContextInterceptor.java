package org.example.cross.conf;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Slf4j
public class AliToTinetServiceContextInterceptor implements HandlerInterceptor {

    public AliToTinetServiceContextInterceptor() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String age = request.getParameter("age");
        String statuses = request.getParameter("name");
        if (statuses != null) {
            System.out.println("statuses参数的值是：" + statuses);
        }
        if (age != null) {
            System.out.println(Integer.valueOf(age));
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

}
