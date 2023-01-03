package com.refactor.strategy;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: zds
 * @Date: 2022/11/07/14:03
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class OptStrategyContextTest {

    @Autowired
    private OptStrategyContext optStrategyContext;

    @Test
    void apply() {
        System.out.println( optStrategyContext.apply(1,2));

    }
}