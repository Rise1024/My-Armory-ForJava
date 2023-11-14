//package com.example.security.conf;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//
///**
// * @author zds
// * @Description
// * @createTime 2022/3/11 11:43
// */
//@Configuration
//public class UserDetailsConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService() throws Exception {
//        // ensure the passwords are encoded properly
//        User.UserBuilder users = User.builder();
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("user").password("{noop}password").roles("USER").build());
//        manager.createUser(users.username("admin").password("{noop}password").roles("USER","ADMIN").build());
//        return manager;
//    }
//}
