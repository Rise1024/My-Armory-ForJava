package com.easemob.weichat.shard;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TestController {
    
    @Autowired
    private TestUserRepository testUserRepository;

    @RequestMapping(value = "/v6/test", method = RequestMethod.GET)
    public void test() {
        for (int i = 1; i < 10; i++) {
            TestUser testUser= new TestUser();
            testUser.setTenantId(i);
            testUser.setName("dsa");
            testUserRepository.save(testUser);
        }
    }

    @RequestMapping(value = "/v6/test1", method = RequestMethod.GET)
    public List<TestUser> test1() {
        List<TestUser> all = testUserRepository.findAll();
        return all;
    }

}
