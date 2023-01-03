package com.refactor.enumeration;

import org.springframework.stereotype.Component;

/**
 * @Auther: zds
 * @Date: 2022/11/07/11:25
 * @Description:
 */

@Component
public class CalculateForEnum {

    public int calculate(int a, int b, Operator operator) {
        return operator.apply(a, b);
    }

}
