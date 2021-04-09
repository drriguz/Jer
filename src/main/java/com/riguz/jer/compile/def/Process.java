package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Process implements Identifiable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return Objects.equals(name, process.name) &&
                Objects.equals(formalParameters, process.formalParameters) &&
                Objects.equals(block, process.block);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, formalParameters, block);
    }
}
