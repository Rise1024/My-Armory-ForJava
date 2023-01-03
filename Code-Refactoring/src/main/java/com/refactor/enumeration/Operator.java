package com.refactor.enumeration;


/**
 * 枚举适合类型固定，可枚举的情况，比如这的操作符;
 * 同时枚举中是可以提供方法实现的，这就是我们可以通过枚举进行重构的原因。
 */

public enum Operator {

    ADD {
        @Override
        public int apply(int a, int b) {
            return a + b;
        }
    },
    SUBTRACTION{
        @Override
        public int apply(int a, int b) {
            return a-b;
        }
    };

    // other operators
    
    public abstract int apply(int a, int b);


}
