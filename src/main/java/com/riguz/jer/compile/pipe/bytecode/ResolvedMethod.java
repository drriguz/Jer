package com.riguz.jer.compile.pipe.bytecode;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class ResolvedMethod {
    private final ResolvedType owner;
    private final String name;
    private final List<ResolvedParameter> parameters;
    private final Method javaMethod;

    private ResolvedMethod(ResolvedType owner, String name, List<ResolvedParameter> parameters, Method javaMethod) {
        this.owner = owner;
        this.name = name;
        this.parameters = Collections.unmodifiableList(parameters);
        this.javaMethod = javaMethod;
    }

    public static ResolvedMethod internal(ResolvedType owner, String name, List<ResolvedParameter> parameters) {
        if (owner.isExternal())
            throw new IllegalArgumentException("Owner is external");
        return new ResolvedMethod(owner, name, parameters, null);
    }

    public static ResolvedMethod external(ResolvedType owner, String name, Method javaMethod) {
        if (!owner.isExternal())
            throw new IllegalArgumentException("Owner is not external");
        return new ResolvedMethod(owner, name, null, javaMethod);
    }

    public boolean isExternal() {
        return owner.isExternal();
    }

    public ResolvedType getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public List<ResolvedParameter> getParameters() {
        return parameters;
    }

    public Method getJavaMethod() {
        return javaMethod;
    }
}
