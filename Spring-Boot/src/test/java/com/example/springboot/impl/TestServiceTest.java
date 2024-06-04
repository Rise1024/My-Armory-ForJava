package com.example.springboot.impl;

import com.example.springboot.ITestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TestServiceTest {
    @Mock
    private ITestService testService;
    @Test
    void test1() {
        Mockito.when(testService.test()).thenReturn("hello");
        String test = testService.test();
        Assert.isTrue("hello".equals(test),"test fail");
    }
}