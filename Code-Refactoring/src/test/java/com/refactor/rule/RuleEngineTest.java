package com.refactor.rule;

import com.refactor.enumeration.Operator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: zds
 * @Date: 2022/11/07/13:51
 * @Description:
 */

class RuleEngineTest {

    @Test
    void process() {

        RuleEngine ruleEngine=new RuleEngine();
        Expression expression=new Expression(1,2, Operator.ADD);
        System.out.println(ruleEngine.process(expression));

    }
}