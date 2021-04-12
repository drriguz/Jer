package com.riguz.jer.compile.pipe.bytecode;

public class Method {
    private final String owner;
    private final String name;
    private final String descriptor;

    public Method(String owner, String name, String descriptor) {
        this.owner = owner;
        this.name = name;
        this.descriptor = descriptor;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
