package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;

public class Method {
    private final String name;
    private final List<Argument> arguments;
    private final String returnType;

    public Method(String name, List<Argument> arguments, String returnType) {
        this.name = name;
        this.arguments = Collections.unmodifiableList(arguments);
        this.returnType = returnType;
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
