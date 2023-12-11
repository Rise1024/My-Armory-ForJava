package com.example.security.controller;

import com.example.security.data.LoginRequest;
import com.example.security.data.LoginResponse;
import com.example.security.data.securityUserEntity;
import com.example.security.exception.CustomerException;
import com.example.security.jpa.SecutiryUserRepository;
import com.example.security.service.UserService;
import com.example.security.workcore.JwtManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private SecutiryUserRepository secutiryUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtManager jwtManager;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        log.info(request.getUsername());
        securityUserEntity user = secutiryUserRepository.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomerException("账号密码错误");
        }
        LoginResponse response = new LoginResponse();
        response.setUsername(user.getUsername())
                .setToken(jwtManager.generate(user.getUsername()));
        return response;
    }


}