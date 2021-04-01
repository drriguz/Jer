package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Statement;

import java.util.Collections;
import java.util.List;

public class NestedBlock extends Statement {
    private final List<Statement> statements;

    public NestedBlock(List<Statement> statements) {
        this.statements = Collections.unmodifiableList(statements);
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
