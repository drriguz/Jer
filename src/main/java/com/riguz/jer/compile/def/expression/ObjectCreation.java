package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectCreation that = (ObjectCreation) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(arguments, that.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, arguments);
    }
}
