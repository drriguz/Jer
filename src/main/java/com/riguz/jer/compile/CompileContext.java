package com.riguz.jer.compile;

import java.util.HashMap;
import java.util.Map;

public class CompileContext {
    private final Map<String, String> fullQualifiedTypes = new HashMap<>();

    public void addFullQualifiedType(String type, String fullName) {
        fullQualifiedTypes.put(type, fullName);
    }

    public String getFullQualifiedType(String type) {
        return fullQualifiedTypes.get(type);
    }
}
