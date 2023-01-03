
package com.refactor.rule;

public interface Rule {
    boolean evaluate(Expression expression);
    int getResult();
}
