package com.refactor.strategy;

import com.refactor.factory.OperationImpl1;
import com.refactor.factory.OperationImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public interface Opt {
    int apply(int a, int b);
}


