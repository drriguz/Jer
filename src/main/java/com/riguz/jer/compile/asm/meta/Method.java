package com.riguz.jer.compile.asm.meta;

import java.util.List;

public class Method {
    private final JavaType providerClass;
    private final List<JavaType> parameters;
    private final JavaType returnType;
    private final boolean isStatic;

    public Method(JavaType providerClass,
                  List<JavaType> parameters,
                  JavaType returnType,
                  boolean isStatic) {
        this.providerClass = providerClass;
        this.parameters = parameters;
        this.returnType = returnType;
        this.isStatic = isStatic;
    }

    public JavaType getProviderClass() {
        return providerClass;
    }

    public List<JavaType> getParameters() {
        return parameters;
    }

    public JavaType getReturnType() {
        return returnType;
    }
}
