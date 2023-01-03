package com.refactor.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class OptStrategyContext{

    private Opt opt;

    public OptStrategyContext( @Qualifier("addOpt")Opt opts) {
        this.opt=opts;
    }

    public int apply( int a, int b) {
        return opt.apply(a, b);
    }
}
