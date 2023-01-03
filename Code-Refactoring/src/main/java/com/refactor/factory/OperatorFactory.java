package com.refactor.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Auther: zds
 * @Date: 2022/11/03/11:27
 * @Description:
 * 工厂模式创建实例工厂
 */

public class OperatorFactory {
    static Map<String, Operation> operationMap = new HashMap<>();
    static {
        operationMap.put("add", new OperationImpl1());
        operationMap.put("divide", new OperationImpl2());
        // more operators
    }

    public static Operation getOperation(String operator) {
        try {
            return Optional.ofNullable(operationMap.get(operator)).orElseThrow(()->new Exception());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
