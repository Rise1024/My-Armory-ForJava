package com.refactor.rule;

import com.refactor.enumeration.Operator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expression {
    private Integer x;
    private Integer y;
    private Operator operator;
}
