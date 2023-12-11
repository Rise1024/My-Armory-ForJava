package com.example.security.service.impl;

import com.example.security.data.securityRoleResourceEntity;
import com.example.security.jpa.SecutiryRoleResourceRepository;
import com.example.security.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: DS
 * @Date: 2023/11/20 17:21
 * @Description:
 **/

@Service
public class RoleResourceServiceImpl implements RoleResourceService {

    @Autowired
    private SecutiryRoleResourceRepository secutiryRoleResourceRepository;

    @Override
    public List<Integer> findResourceIdByRoleId(List<Integer> roleId) {
        return secutiryRoleResourceRepository.findResourceIdByRoleId(roleId);
    }

    @Override
    public void save(securityRoleResourceEntity s) {
        secutiryRoleResourceRepository.saveAndFlush(s);
    }
}
