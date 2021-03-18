package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;

public class FunctionSignature {
    protected final String name;
    protected final List<Parameter> formalParameters;
    protected final String returnType;


    public FunctionSignature(String name,
                             List<Parameter> formalParameters, String returnType) {
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

    public String getReturnType() {
        return returnType;
    }
}
