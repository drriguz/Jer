package com.riguz.jer.compile.util;

import java.util.List;

public final class SignatureBuilder {
    private SignatureBuilder() {
    }

    public static String getMethodDescriptor(List<String> paramTypes, String returnType) {
        String params = String.join(";", paramTypes);
        return String.format("(%s)%s", params, returnType);
    }
}
