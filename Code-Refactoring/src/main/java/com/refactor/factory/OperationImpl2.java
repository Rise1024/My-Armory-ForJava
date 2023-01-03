package com.refactor.factory;

/**
 * @Auther: zds
 * @Date: 2022/11/03/11:26
 * @Description:
 */
public class OperationImpl2 implements Operation{
    @Override
    public int apply(int a, int b) {
        return a-b;
    }
}
