package com.example.springboot.data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author zds
 * @Description
 * @createTime 2022/2/25 11:59
 */
public class UserTest2 {

    private String name;
    private int age;

    public UserTest2() {
    }
    @PostConstruct
    public void init() {
        System.out.println("初始化UserTest");
    }
    @PreDestroy
    public void destory() {
        System.out.println("销毁UserTest");
    }
}
