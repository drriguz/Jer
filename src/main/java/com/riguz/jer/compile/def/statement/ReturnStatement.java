package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

public class ReturnStatement extends Statement {
    private final Expression returnValue;

    public ReturnStatement(Expression returnValue) {
        this.returnValue = returnValue;
    }

    public Expression getReturnValue() {
        return returnValue;
    }
}
