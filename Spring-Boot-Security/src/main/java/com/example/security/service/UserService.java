package com.example.security.service;

import com.example.security.data.LoginRequest;
import com.example.security.data.LoginResponse;
import com.example.security.data.securityUserEntity;

import java.util.List;

/**
 * @Author: DS
 * @Date: 2023/11/20 17:21
 * @Description:
 **/


public interface UserService {
    securityUserEntity save(securityUserEntity securityUserEntity);

    List<securityUserEntity> getUser();

    void updUser(securityUserEntity securityUserEntity);

    void creatUser(securityUserEntity securityUserEntity);


    void delUser(Integer userId);

}
