package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

public class VariableReference extends Expression {
    private final String variableName;
    private final boolean self;

    public VariableReference(String variableName, boolean self) {
        this.variableName = variableName;
        this.self = self;
    }

    public String getVariableName() {
        return variableName;
    }

    public boolean isSelf() {
        return self;
    }
}
