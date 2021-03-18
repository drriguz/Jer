package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

import java.util.List;

public class ObjectCreation extends Expression {
    private final String type;
    private final List<Expression> arguments;

    public ObjectCreation(String type, List<Expression> arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    public String getType() {
        return type;
    }

    public List<Expression> getArguments() {
        return arguments;
    }
}
