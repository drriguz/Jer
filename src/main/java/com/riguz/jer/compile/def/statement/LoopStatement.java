package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoopStatement that = (LoopStatement) o;
        return Objects.equals(condition, that.condition) &&
                Objects.equals(statement, that.statement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, statement);
    }
}
