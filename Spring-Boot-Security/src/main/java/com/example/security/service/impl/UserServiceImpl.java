package com.example.security.service.impl;

import com.example.security.data.LoginRequest;
import com.example.security.data.LoginResponse;
import com.example.security.data.securityUserEntity;
import com.example.security.data.securityUserRoleEntity;
import com.example.security.exception.CustomerException;
import com.example.security.jpa.SecutiryUserRepository;
import com.example.security.service.RoleResourceService;
import com.example.security.service.UserRoleService;
import com.example.security.service.UserService;
import com.example.security.workcore.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: DS
 * @Date: 2023/11/20 14:00
 * @Description:
 **/

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private SecutiryUserRepository secutiryUserRepository;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleResourceService roleResourceService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        securityUserEntity user = secutiryUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("没有找到该用户");
        }
        List<Integer> securityUserRoleEntities = userRoleService.findByUserId(user.getUserId());
        if (!CollectionUtils.isEmpty(securityUserRoleEntities)){
            List<Integer> roleResourceIdByRoleId = roleResourceService.findResourceIdByRoleId(securityUserRoleEntities);
            return new User(user.getUsername(),user.getPassword(), roleResourceIdByRoleId.stream().map(String::valueOf).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
        throw new UsernameNotFoundException("没有找到该用户权限");
    }

    @Override
    public securityUserEntity save(securityUserEntity securityUserEntity) {
       return secutiryUserRepository.saveAndFlush(securityUserEntity);
    }

    @Override
    public List<securityUserEntity> getUser() {
        return secutiryUserRepository.findAll();
    }

    @Override
    public void updUser(securityUserEntity securityUserEntity) {
        secutiryUserRepository.saveAndFlush(securityUserEntity);
    }

    @Override
    public void creatUser(securityUserEntity securityUserEntity) {
        com.example.security.data.securityUserEntity securityUserEntity1 = secutiryUserRepository.findByUsername(securityUserEntity.getUsername());
        if(securityUserEntity1!=null){
            return;
        }
        secutiryUserRepository.saveAndFlush(securityUserEntity);
    }

    @Override
    public void delUser(Integer userId) {
        secutiryUserRepository.deleteById(userId);
    }
}
