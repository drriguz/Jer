package com.riguz.jer.compile.pipe.bytecode;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.riguz.jer.compile.util.SignatureBuilder.getMethodDescriptor;

public class ResolvedMethod {
    private final ResolvedType owner;
    private final String name;
    private final List<ResolvedParameter> parameters;

    public ResolvedMethod(ResolvedType owner, String name, List<ResolvedParameter> parameters) {
        this.owner = owner;
        this.name = name;
        this.parameters = Collections.unmodifiableList(parameters);
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

    public String getDescriptor() {
        return getMethodDescriptor(
                parameters.stream()
                        .map(p -> p.getType().getDescriptor())
                        .collect(Collectors.toList()),
                "V");
    }
}
