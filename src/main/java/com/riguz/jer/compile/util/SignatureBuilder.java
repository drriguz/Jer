package com.riguz.jer.compile.util;

import java.util.List;
import java.util.stream.Collectors;

public final class SignatureBuilder {
    private SignatureBuilder() {
    }

    public static String getMethodDescriptor(List<String> paramTypes, String returnType) {
        String params = paramTypes
                .stream()
                .collect(Collectors.joining(";"));
        String returnTypeDescriptor = returnType == null ?
                "V" : String.format("L%s;", returnType);
        return String.format("(%s)%s", params, returnTypeDescriptor);
    }
}
