package com.refactor.rule;

import com.refactor.enumeration.Operator;

public class AddRule implements Rule {

    private int result;

    /**
     * 规则
     * @param expression
     * @return
     */
    @Override
    public boolean evaluate(Expression expression) {
        boolean evalResult = false;
        if (expression.getOperator() == Operator.ADD) {
            this.result = expression.getX() + expression.getY();
            evalResult = true;
        }
        return evalResult;
    }


    /**
     * 结果
     * @return
     */
    @Override
    public int getResult() {
        return result;
    }
}
