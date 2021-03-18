package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

public class SelectionStatement extends Statement {
    private final Expression condition;
    private final Statement statement;
    private final Statement opposite;

    public SelectionStatement(Expression condition,
                              Statement statement,
                              Statement opposite) {
        this.condition = condition;
        this.statement = statement;
        this.opposite = opposite;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getStatement() {
        return statement;
    }

    public Statement getOpposite() {
        return opposite;
    }
}
