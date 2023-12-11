package com.example.security.workcore;

import com.example.security.data.securityResourceEntity;
import com.example.security.exception.CustomerException;
import com.example.security.jpa.SecutiryResourceRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: DS
 * @Date: 2023/11/20 14:00
 * @Description:
 **/

@Slf4j
@Component
public class CustomSecurityMetadataSource implements SecurityMetadataSource {

    @Autowired
    private SecutiryResourceRepository secutiryResourceRepository;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) {
        // 该对象是Spring Security帮我们封装好的，可以通过该对象获取request等信息
        FilterInvocation filterInvocation = (FilterInvocation) object;
        HttpServletRequest request = filterInvocation.getRequest();
        List<securityResourceEntity> allRESOURCES = secutiryResourceRepository.findAll();
        for (securityResourceEntity resource : allRESOURCES) {
            String[] split = resource.getResourcePath().split(":");
            AntPathRequestMatcher ant = new AntPathRequestMatcher(split[1]);
            if (request.getMethod().equals(split[0]) && ant.matches(request)) {
                return Collections.singletonList(new SecurityConfig(String.valueOf(resource.getResourceId())));
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
