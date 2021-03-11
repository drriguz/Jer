package com.riguz.jer.compile.def;

public class Constant {
    private final String name;
    private final String type;
    private final Expression value;

    public Constant(String name, String type, Expression value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Expression getValue() {
        return value;
    }
}
