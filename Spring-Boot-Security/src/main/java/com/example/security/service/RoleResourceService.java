package com.example.security.service;

import com.example.security.data.securityRoleResourceEntity;

import java.util.List;

/**
 * @Author: DS
 * @Date: 2023/11/20 17:21
 * @Description:
 **/


public interface RoleResourceService {
    List<Integer> findResourceIdByRoleId(List<Integer> roleId);

    void  save(securityRoleResourceEntity s);
}
