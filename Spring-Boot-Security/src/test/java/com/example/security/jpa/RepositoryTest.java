package com.example.security.jpa;

import com.example.security.SpringBootBaseTest;
import com.example.security.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: DS
 * @Date: 2023/11/21 18:30
 * @Description:
 **/

public class RepositoryTest extends SpringBootBaseTest {
    @Autowired
    private UserService userService;

    @Test
    public void initTest() {
        System.out.println(userService.getUser());
    }
}
