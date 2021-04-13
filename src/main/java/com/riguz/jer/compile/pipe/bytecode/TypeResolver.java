package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.def.VariableType;
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

    public ResolvedType resolveType(String fullName) {
        return tryResolveImportedType(fullName, true);
    }

    public ResolvedType resolveExternalType(Class<?> javaClass) {
        String typeName = javaClass.getName();
        int arrayEnd = typeName.lastIndexOf('[');
        if (arrayEnd < 0)
            return ResolvedType.external(typeName.replaceAll("\\.", "/"), javaClass);
        else {
            int arrayDimensions = arrayEnd + 1;
            String baseName = typeName.substring(arrayEnd + 1);
            return ResolvedType.externalArray(baseName.replaceAll("\\.", "/"),
                    javaClass,
                    arrayDimensions);
        }

    }

    public ResolvedType resolveType(ClassDefinition scope, VariableType type)
            throws CompileException {
        ResolvedType baseType = resolveBaseType(scope, type.getBaseType());
        return type.isArray() ?
                baseType.toArray(type.getArrayDimensions())
                : baseType;
    }

    private ResolvedType resolveBaseType(ClassDefinition scope, String baseType) {
        ResolvedType resolved = tryResolveFromSamePackage(scope, baseType);
        if (resolved == null)
            resolved = tryResolveImportedType(scope, baseType);
        if (resolved != null)
            return resolved;
        else throw new CompileException("Unable to resolve type:" + baseType);
    }

    private ResolvedType tryResolveFromSamePackage(ClassDefinition scope, String baseType) {
        List<ClassDefinition> packageClasses = packageTypes.getOrDefault(
                scope.getPackageName(), Collections.emptyList());
        return packageClasses.stream()
                .filter(c -> c.getClassName().equals(baseType))
                .findAny()
                .map(c -> ResolvedType.internal(c.getFullName(), c))
                .orElse(null);
    }

    private ResolvedType tryResolveImportedType(ClassDefinition scope, String baseType) {
        String fullName = scope.getImportedClasses().get(baseType);
        if (fullName != null)
            return tryResolveImportedType(fullName, true);

        fullName = autoImportedTypes.getFullQualifiedType(baseType);
        if (fullName != null)
            return tryResolveImportedType(fullName, false);
        else
            throw new CompileException("Type could not be resolved:" + baseType);
    }

    private ResolvedType tryResolveImportedType(String fullName, boolean maybeInternal) {
        if (maybeInternal) {
            ClassDefinition classDefinition = findInSource(fullName);
            if (classDefinition != null)
                return ResolvedType.internal(fullName, classDefinition);
        }
        try {
            Class<?> externalClass = Class.forName(fullName.replaceAll("/", "\\."));
            return ResolvedType.external(fullName, externalClass);
        } catch (ClassNotFoundException e) {
            throw new CompileException("Class could not be found either from source or classpath:" + fullName);
        }
    }

    private ClassDefinition findInSource(String fullName) {
        return context.getSources()
                .stream()
                .filter(s -> s.getFullName().equals(fullName))
                .findAny()
                .orElse(null);
    }
}
