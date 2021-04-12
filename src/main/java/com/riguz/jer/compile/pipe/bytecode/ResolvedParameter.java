package com.riguz.jer.compile.pipe.bytecode;

public class ResolvedParameter {
    private final String name;
    private final ResolvedType type;

    public ResolvedParameter(String name, ResolvedType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ResolvedType getType() {
        return type;
    }
}
