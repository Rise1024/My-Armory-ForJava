package com.example.security.controller;

import com.example.security.data.securityUserEntity;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/user")
    public List<securityUserEntity> getUser() {
        return userService.getUser();
    }

    @PostMapping("/user")
    public void creatUser(@RequestBody securityUserEntity securityUserEntity) {
        securityUserEntity.setPassword(passwordEncoder.encode(securityUserEntity.getPassword()));
        userService.creatUser(securityUserEntity);
    }


    @PutMapping("/user")
    public void updUser(@RequestBody securityUserEntity securityUserEntity) {
         userService.updUser(securityUserEntity);
    }

    @DeleteMapping("/user")
    public void delUser(Integer userId) {
        userService.delUser(userId);
    }
}