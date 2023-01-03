package com.refactor.strategy;

import com.refactor.factory.OperationImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "devideOpt")
public class devideOpt implements Opt {

    @Override
    public int apply(int a, int b) {
       return a-b;
    }
}