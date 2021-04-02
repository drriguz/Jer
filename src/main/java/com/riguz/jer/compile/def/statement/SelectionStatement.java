package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectionStatement that = (SelectionStatement) o;
        return Objects.equals(condition, that.condition) &&
                Objects.equals(statement, that.statement) &&
                Objects.equals(opposite, that.opposite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(condition, statement, opposite);
    }
}
