package com.example.springboot.data;

/**
 * @author zds
 * @Description
 * @createTime 2022/2/25 11:55
 */

public class UserTest {
    private String name;
    private int age;

    public UserTest() {
    }

    public void init() {
        System.out.println("初始化UserTest");
    }
    public void destory() {
        System.out.println("销毁UserTest");
    }
}
