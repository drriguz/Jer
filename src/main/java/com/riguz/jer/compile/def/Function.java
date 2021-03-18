package com.riguz.jer.compile.def;

import java.util.List;

public class Function extends FunctionSignature {
    private final Block block;

    public Function(String name,
                    List<Parameter> formalParameters,
                    String returnType,
                    Block block) {
        super(name, formalParameters, returnType);
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }
}
