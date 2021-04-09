package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.exception.CompileException;
import com.riguz.jer.compile.pipe.pre.ClassDefinition;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class TypeResolver {
    private static class TypeMapping {
        private final Map<String, String> fullQualifiedTypes;

        public TypeMapping(Map<String, String> fullQualifiedTypes) {
            this.fullQualifiedTypes = Collections.unmodifiableMap(fullQualifiedTypes);
        }

        public String getFullQualifiedType(String type) {
            return fullQualifiedTypes.get(type);
        }
    }

    private final Context context;
    private final Map<String, List<ClassDefinition>> packageTypes;

    private static final TypeMapping autoImportedTypes =
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

    public TypeResolver(Context context) {
        this.context = context;
        this.packageTypes = context.getSources()
                .stream()
                .collect(Collectors.groupingBy(ClassDefinition::getPackageName));
    }

    public JavaType resolveTypeDescriptor(ClassDefinition scope, String type)
            throws CompileException {
        int arrayEnd = type.lastIndexOf("[");
        if (arrayEnd >= 0) {
            String arrayDescriptor = type.substring(0, arrayEnd + 1);
            String baseType = type.substring(arrayEnd + 1);
            return JavaType.of(resolveBaseType(scope, baseType), arrayDescriptor);
        } else {
            return resolveBaseType(scope, type);
        }
    }

    private JavaType resolveBaseType(ClassDefinition scope, String baseType) {
        JavaType resolved = tryResolveFromSamePackage(scope, baseType);
        if (resolved == null)
            resolved = tryResolveImportedType(scope, baseType);
        if (resolved != null)
            return resolved;
        else throw new CompileException("Unable to resolve type:" + baseType);
    }

    private JavaType tryResolveFromSamePackage(ClassDefinition scope, String baseType) {
        List<ClassDefinition> packageClasses = packageTypes.getOrDefault(
                scope.getPackageName(), Collections.emptyList());
        return packageClasses.stream()
                .filter(c -> c.getClassName().equals(baseType))
                .findAny()
                .map(c -> JavaType.of(c.getFullName(), false))
                .orElse(null);
    }

    private JavaType tryResolveImportedType(ClassDefinition scope, String baseType) {
        String fullName = scope.getImportedClasses().get(baseType);
        if (fullName != null)
            return JavaType.of(fullName, false);
        else {
            fullName = autoImportedTypes.getFullQualifiedType(baseType);

            return fullName == null ? null : JavaType.of(fullName, true);
        }
    }
}
