package com.example.security.service.impl;

import com.example.security.data.securityRoleEntity;
import com.example.security.jpa.SecutiryRoleRepository;
import com.example.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: DS
 * @Date: 2023/11/21 17:44
 * @Description:
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SecutiryRoleRepository secutiryRoleRepository;
    @Override
    public securityRoleEntity save(securityRoleEntity s) {
      return  secutiryRoleRepository.saveAndFlush(s);
    }
}
