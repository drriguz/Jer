package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

public class ExpressionStatement extends Statement {
    private final Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }
}
