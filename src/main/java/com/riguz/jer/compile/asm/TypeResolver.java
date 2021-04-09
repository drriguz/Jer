package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.CompileContext;
import com.riguz.jer.compile.asm.meta.JavaType;
import com.riguz.jer.compile.def.Identifiable;
import com.riguz.jer.compile.exception.CompileException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.riguz.jer.compile.asm.TypeMapping.autoImportedTypes;

public final class TypeResolver {
    private final TypeMapping userImportedTypes;
    private final Scope scope;

    private TypeResolver(Scope scope) {
        this.scope = scope;
        this.userImportedTypes = new TypeMapping(
                createMapping(scope.getScript().getImportedTypes())
        );
    }

    private Map<String, String> createMapping(List<String> importedTypes) {
        return importedTypes
                .stream()
                .collect(Collectors.toMap(t -> {
                    int lastSlash = t.lastIndexOf('/');
                    return t.substring(lastSlash + 1);
                }, t -> t));
    }

    public static String resolveTypeDescriptor(CompileContext context, String type)
            throws CompileException {
        int arrayEnd = type.lastIndexOf("[");
        final String fullName;
//        if (arrayEnd >= 0) {
//            String arrayMarker = type.substring(0, arrayEnd + 1);
//            String baseType = type.substring(arrayEnd + 1);
//            fullName = resolveBaseType(context, baseType);
//            return String.format("%sL%s;", arrayMarker, fullName);
//        } else {
//            fullName = resolveBaseType(context, type);
//            return String.format("L%s;", fullName);
//        }
        return null;
    }

//    public static JavaType resolveType(Scope scope, String type) {
//        int arrayEnd = type.lastIndexOf("[");
//        final String fullName;
//        if (arrayEnd >= 0) {
//            String arrayMarker = type.substring(0, arrayEnd + 1);
//            String baseType = type.substring(arrayEnd + 1);
//            fullName = resolveBaseType(context, baseType);
//            return String.format("%sL%s;", arrayMarker, fullName);
//        } else {
//            fullName = resolveBaseType(context, type);
//            return String.format("L%s;", fullName);
//        }
//    }

    private String resolveBaseType(String baseType) {
        String fullQualifiedType = userImportedTypes.getFullQualifiedType(baseType);
        if (fullQualifiedType == null)
            fullQualifiedType = autoImportedTypes.getFullQualifiedType(baseType);
        if (fullQualifiedType == null) {

        }

        return null;
    }

    private JavaType tryResolveImportedType(String baseType) {
        String fullQualifiedType = userImportedTypes.getFullQualifiedType(baseType);
        if (fullQualifiedType == null)
            fullQualifiedType = autoImportedTypes.getFullQualifiedType(baseType);
        if (fullQualifiedType != null) {

        }
        return null;
    }

    private JavaType tryResolveLocalType(String baseType) {
        if (Stream.concat(scope.getScript().getTypes().stream(),
                scope.getScript().getAbstracts().stream())
                .anyMatch(t -> t.getName().equals(baseType))) {
            return new JavaType(baseType,
                    scope.getScript().getPackageName(),
                    false,
                    false
            );
        }
        return null;
    }
}
