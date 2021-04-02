package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessStatement that = (ProcessStatement) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(arguments, that.arguments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, arguments);
    }
}
