package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

import java.util.Collections;
import java.util.List;

public class MethodInvocation {
    private final String instance;
    private final String methodName;
    private final List<Expression> arguments;

    public MethodInvocation(String instance, String methodName, List<Expression> arguments) {
        this.instance = instance;
        this.methodName = methodName;
        this.arguments = Collections.unmodifiableList(arguments);
    }

    public String getInstance() {
        return instance;
    }

    public String getMethodName() {
        return methodName;
    }

    public List<Expression> getArguments() {
        return arguments;
    }
}
