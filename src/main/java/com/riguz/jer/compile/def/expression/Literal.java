package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

public abstract class Literal extends Expression {
    protected final Object value;

    protected Literal(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
