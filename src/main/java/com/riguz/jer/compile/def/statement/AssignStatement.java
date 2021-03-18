package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

public class AssignStatement extends Statement {
    private final String variableName;
    private final Expression expression;

    public AssignStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public Expression getExpression() {
        return expression;
    }
}
