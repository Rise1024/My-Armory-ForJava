package com.refactor.enumeration;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: zds
 * @Date: 2022/11/07/11:26
 * @Description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
class CalculateTest {


    @Autowired
    private CalculateForEnum calculateForEnum;


    @Test
    void calculateEnum() {
        int calculate1 = calculateForEnum.calculate(1, 2, Operator.ADD);
        System.out.println(calculate1);
    }

}
