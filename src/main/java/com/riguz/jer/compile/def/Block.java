package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;

public class Block {
    private final List<Statement> statements;

    public Block(List<Statement> statements) {
        this.statements = Collections.unmodifiableList(statements);
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
