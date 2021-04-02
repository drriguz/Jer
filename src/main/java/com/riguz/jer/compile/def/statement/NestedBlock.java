package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Statement;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class NestedBlock extends Statement {
    private final List<Statement> statements;

    public NestedBlock(List<Statement> statements) {
        this.statements = Collections.unmodifiableList(statements);
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NestedBlock that = (NestedBlock) o;
        return Objects.equals(statements, that.statements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statements);
    }
}
