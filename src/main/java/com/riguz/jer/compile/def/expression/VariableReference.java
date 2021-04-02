package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableReference that = (VariableReference) o;
        return self == that.self &&
                Objects.equals(variableName, that.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName, self);
    }
}
