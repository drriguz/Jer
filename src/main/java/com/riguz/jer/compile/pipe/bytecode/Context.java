package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.pipe.pre.ClassDefinition;

import java.util.List;

public class Context {
    private final List<ClassDefinition> sources;

    public Context(List<ClassDefinition> sources) {
        this.sources = sources;
    }

    public List<ClassDefinition> getSources() {
        return sources;
    }
}
