package com.refactor.strategy;

import org.springframework.stereotype.Component;

@Component(value = "addOpt")
public class AddOpt implements Opt {

    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}