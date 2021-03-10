package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;

public class Method {
    private final String name;
    private final List<Argument> arguments;
    private final String returnType;
    private final List<Statement> statements;

    public Method(String name,
                  List<Argument> arguments,
                  String returnType,
                  List<Statement> statements) {
        this.name = name;
        this.arguments = Collections.unmodifiableList(arguments);
        this.returnType = returnType;
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public String getReturnType() {
        return returnType;
    }

    public static class Argument {
        private final String name;
        private final String type;

        public Argument(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }
}
