package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;

public class Process {
    private final String name;
    private final List<Parameter> formalParameters;
    private final Block block;

    public Process(String name,
                   List<Parameter> formalParameters,
                   Block block) {
        this.name = name;
        this.formalParameters = Collections.unmodifiableList(formalParameters);
        this.block = block;
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getFormalParameters() {
        return formalParameters;
    }

    public Block getBlock() {
        return block;
    }
}