package com.riguz.jer.compile;

import java.util.HashMap;
import java.util.Map;

public class CompileContext {
    private final Map<String, String> fullQualifiedTypes = new HashMap<>();

    public CompileContext() {
        addJvmDefaultImports();
    }

    public void addFullQualifiedType(String type, String fullName) {
        fullQualifiedTypes.put(type, fullName);
    }

    public String getFullQualifiedType(String type) {
        return fullQualifiedTypes.get(type);
    }

    private void addJvmDefaultImports() {
        addFullQualifiedType("Byte", "java/lang/Byte");
        addFullQualifiedType("Boolean", "java/lang/Boolean");
        addFullQualifiedType("Integer", "java/lang/Integer");
        addFullQualifiedType("Float", "java/lang/Float");
        addFullQualifiedType("Short", "java/lang/Short");
        addFullQualifiedType("Long", "java/lang/Long");
        addFullQualifiedType("Double", "java/lang/Double");
        addFullQualifiedType("Character", "java/lang/Character");
        addFullQualifiedType("System", "java/lang/System");
        addFullQualifiedType("String", "java/lang/String");
    }
}
