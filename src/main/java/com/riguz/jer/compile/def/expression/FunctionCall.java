package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

import java.util.Collections;
import java.util.List;

public class FunctionCall extends Expression {
    private final Expression instance;
    private final String functionName;
    private final List<Expression> arguments;

    public FunctionCall(Expression instance,
                        String functionName,
                        List<Expression> arguments) {
        this.instance = instance;
        this.functionName = functionName;
        this.arguments = Collections.unmodifiableList(arguments);
    }

    public Expression getInstance() {
        return instance;
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<Expression> getArguments() {
        return arguments;
    }
}
