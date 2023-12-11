package com.example.security.service.impl;

import com.example.security.data.securityRoleEntity;
import com.example.security.data.securityUserRoleEntity;
import com.example.security.jpa.SecutiryUserRoleRepository;
import com.example.security.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: DS
 * @Date: 2023/11/20 17:21
 * @Description:
 **/

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private SecutiryUserRoleRepository secutiryUserRoleRepository;

    @Override
    public List<Integer> findByUserId(Integer userId) {
        List<Integer> roleIdByUserId = secutiryUserRoleRepository.findRoleIdByUserId(userId);
        return roleIdByUserId;
    }

    @Override
    public void save(securityUserRoleEntity s) {
        secutiryUserRoleRepository.saveAndFlush(s);
    }
}
