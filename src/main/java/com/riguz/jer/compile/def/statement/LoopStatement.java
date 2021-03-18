package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

public class LoopStatement extends Statement {
    private final Expression condition;
    private final Statement statement;

    public LoopStatement(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getStatement() {
        return statement;
    }
}
