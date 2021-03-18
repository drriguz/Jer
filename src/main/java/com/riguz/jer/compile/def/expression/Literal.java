package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

public class Literal extends Expression {
    private final String value;

    public Literal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
