package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Constructor {
    private final List<Parameter> formalParameters;
    private final Block block;

    public Constructor(List<Parameter> formalParameters,
                       Block block) {
        this.formalParameters = Collections.unmodifiableList(formalParameters);
        this.block = block;
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
        Constructor that = (Constructor) o;
        return Objects.equals(formalParameters, that.formalParameters) &&
                Objects.equals(block, that.block);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formalParameters, block);
    }
}
