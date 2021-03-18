package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;

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
}
