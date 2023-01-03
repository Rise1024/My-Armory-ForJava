package com.refactor.factory;

import org.springframework.stereotype.Service;

/**
 * @Auther: zds
 * @Date: 2022/11/03/13:58
 * @Description:
 * 工厂模式执行器
 */

@Service
public class Calculate {
    public int calculateUsingFactory(int a, int b, String operator) {
        Operation targetOperation = OperatorFactory
                .getOperation(operator);
        return targetOperation.apply(a, b);
    }

}
