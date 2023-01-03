package com.example.springbean.lifeaycle.config;


import com.example.springbean.lifeaycle.data.UserTest;
import com.example.springbean.lifeaycle.data.UserTest1;
import com.example.springbean.lifeaycle.data.UserTest2;
import com.example.springbean.lifeaycle.service.MyBeanInstantiationPostProcessor;
import com.example.springbean.lifeaycle.service.MyBeanPostProcessor;
import com.example.springbean.regist.domain.Hello;
import com.example.springbean.regist.domain.User;
import com.example.springbean.regist.factory.CherryFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zds
 */
@Configuration
@Import({MyBeanInstantiationPostProcessor.class})
public class WebConfigTestTwo {

    @Bean(initMethod = "init",destroyMethod = "destory")
    public UserTest userTest() {
        System.out.println("往IOC容器中注册UserTest bean");
        return new UserTest();
    }

//    @Bean
//    public UserTest1 userTest1() {
//        System.out.println("往IOC容器中注册UserTest1 bean");
//        return new UserTest1();
//    }
//
//    @Bean
//    public UserTest2 userTest2() {
//        System.out.println("往IOC容器中注册UserTest2 bean");
//        return new UserTest2();
//    }

}
