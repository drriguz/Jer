package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

public class NestedExpression extends Expression {
    private final Expression instance;
    private final Expression invoker;

    public NestedExpression(Expression instance, Expression invoker) {
        this.instance = instance;
        this.invoker = invoker;
    }

    public Expression getInstance() {
        return instance;
    }

    public Expression getInvoker() {
        return invoker;
    }
}
