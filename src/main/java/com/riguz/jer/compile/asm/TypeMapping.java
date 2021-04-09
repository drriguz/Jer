package com.riguz.jer.compile.asm;

import java.util.Collections;
import java.util.Map;

public class TypeMapping {
    private final Map<String, String> fullQualifiedTypes;

    public TypeMapping(Map<String, String> fullQualifiedTypes) {
        this.fullQualifiedTypes = Collections.unmodifiableMap(fullQualifiedTypes);
    }

    public String getFullQualifiedType(String type) {
        return fullQualifiedTypes.get(type);
    }

    public static final TypeMapping autoImportedTypes =
            new TypeMapping(
                    Map.of("Byte", "java/lang/Byte",
                            "Boolean", "java/lang/Boolean",
                            "Integer", "java/lang/Integer",
                            "Float", "java/lang/Float",
                            "Short", "java/lang/Short",
                            "Long", "java/lang/Long",
                            "Double", "java/lang/Double",
                            "Character", "java/lang/Character",
                            "System", "java/lang/System",
                            "String", "java/lang/String")
            );
}
