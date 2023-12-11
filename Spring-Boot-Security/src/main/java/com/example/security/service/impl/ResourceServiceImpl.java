package com.example.security.service.impl;

import com.example.security.data.securityResourceEntity;
import com.example.security.jpa.SecutiryResourceRepository;
import com.example.security.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: DS
 * @Date: 2023/11/20 17:21
 * @Description:
 **/

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private SecutiryResourceRepository secutiryResourceRepository;

    @Override
    public securityResourceEntity save(securityResourceEntity s) {
        return secutiryResourceRepository.saveAndFlush(s);
    }
}
