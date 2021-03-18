package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;

public class Abstract {
    private final String name;
    private final List<Parameter> properties;
    private final List<FunctionSignature> functionSignatures;


    public Abstract(String name,
                    List<Parameter> properties,
                    List<FunctionSignature> functionSignatures) {
        this.name = name;
        this.properties = Collections.unmodifiableList(properties);
        this.functionSignatures = Collections.unmodifiableList(functionSignatures);
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getProperties() {
        return properties;
    }

    public List<FunctionSignature> getFunctionSignatures() {
        return functionSignatures;
    }
}
