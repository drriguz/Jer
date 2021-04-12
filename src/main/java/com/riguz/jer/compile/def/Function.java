package com.riguz.jer.compile.def;

import java.util.List;
import java.util.Objects;

public class Function extends FunctionSignature implements Identifiable {
    private final Block block;

    public Function(String name,
                    List<Parameter> formalParameters,
                    VariableType returnType,
                    Block block) {
        super(name, formalParameters, returnType);
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return Objects.equals(block, function.block);
    }

    @Override
    public int hashCode() {
        return Objects.hash(block);
    }
}
