package com.riguz.jer.compile.util;

import com.riguz.jer.compile.def.Parameter;

import java.util.List;
import java.util.stream.Collectors;

public final class SignatureBuilder {
    private SignatureBuilder() {
    }

    public static String getMethodDescriptor(List<Parameter> parameters, String returnType) {
        String params = parameters
                .stream()
                .map(Parameter::getType)
                .collect(Collectors.joining(","));
        return String.format("(%s)%s", params, returnType);
    }
}
