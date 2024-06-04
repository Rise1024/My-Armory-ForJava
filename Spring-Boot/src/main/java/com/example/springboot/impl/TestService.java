package com.example.springboot.impl;

import com.example.springboot.ITestService;
import org.springframework.stereotype.Service;

/**
 * @Author: DS
 * @Date: 2024/5/24 15:42
 * @Description:
 **/
@Service
public class TestService implements ITestService {
    @Override
    public String test() {
       return "test";
    }

    @Override
    public String queryData() {
        return "query";
    }
}
