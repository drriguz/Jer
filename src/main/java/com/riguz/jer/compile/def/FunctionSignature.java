package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FunctionSignature {
    protected final String name;
    protected final List<Parameter> formalParameters;
    protected final VariableType returnType;


    public FunctionSignature(String name,
                             List<Parameter> formalParameters,
                             VariableType returnType) {
        this.name = name;
        this.formalParameters = Collections.unmodifiableList(formalParameters);
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getFormalParameters() {
        return formalParameters;
    }

    public VariableType getReturnType() {
        return returnType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionSignature that = (FunctionSignature) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(formalParameters, that.formalParameters) &&
                Objects.equals(returnType, that.returnType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, formalParameters, returnType);
    }
}
