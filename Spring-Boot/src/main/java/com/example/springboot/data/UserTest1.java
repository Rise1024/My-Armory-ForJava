package com.example.springboot.data;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author zds
 * @Description
 * @createTime 2022/2/25 11:59
 */
public class UserTest1 implements InitializingBean, DisposableBean {
    private String name;
    private int age;

    public UserTest1() {
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("销毁UserTest1");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化UserTestq1");

    }
}
