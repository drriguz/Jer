package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

import java.util.Collections;
import java.util.List;

public class ProcessStatement extends Statement {
    private final String name;
    private final List<Expression> arguments;

    public ProcessStatement(String name, List<Expression> arguments) {
        this.name = name;
        this.arguments = Collections.unmodifiableList(arguments);
    }

    public String getName() {
        return name;
    }

    public List<Expression> getArguments() {
        return arguments;
    }
}
