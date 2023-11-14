package com.example.security.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zds
 * @Description
 * @createTime 2022/3/10 16:01
 */
@Configuration
@EnableWebSecurity
public class ActuatorWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    //定义可以访问/actuator的ip地址数组，只有指定ip在指定数组里面并且登陆才可以访问
    String [] ipAddresses = new String[] {"127.0.0.1"};

    /**
     * 放开所有接口
     * @param http
     * @throws Exception
     */

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/hello/**").permitAll() // 忽略/public路径下的接口
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .and()
                    .httpBasic();
        }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/hello/**").anonymous() // 禁用/public路径下的接口的安全性
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这里也可以配置用户密码
        super.configure(auth);
    }

}
