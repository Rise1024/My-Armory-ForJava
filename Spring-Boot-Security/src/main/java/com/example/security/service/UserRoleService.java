package com.example.security.service;

import com.example.security.data.securityRoleEntity;
import com.example.security.data.securityUserRoleEntity;

import java.util.List;

/**
 * @Author: DS
 * @Date: 2023/11/20 17:21
 * @Description:
 **/


public interface UserRoleService {
    List<Integer> findByUserId(Integer userId);

   void save(securityUserRoleEntity s);


}
