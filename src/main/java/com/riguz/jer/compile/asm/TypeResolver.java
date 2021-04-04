package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.CompileContext;
import com.riguz.jer.compile.exception.CompileException;

public final class TypeResolver {
    private TypeResolver() {

    }

    public static String resolveTypeDescriptor(CompileContext context, String type)
            throws CompileException {
        int arrayEnd = type.lastIndexOf("[");
        final String fullName;
        if (arrayEnd >= 0) {
            String arrayMarker = type.substring(0, arrayEnd + 1);
            String baseType = type.substring(arrayEnd + 1);
            fullName = resolveBaseType(context, baseType);
            return String.format("%sL%s;", arrayMarker, fullName);
        } else {
            fullName = resolveBaseType(context, type);
            return String.format("L%s;", fullName);
        }
    }

    private static String resolveBaseType(CompileContext context, String type)
            throws CompileException {
        String fullName = context.getFullQualifiedType(type);
        if (fullName != null)
            return fullName;
        else
            throw new CompileException("Type not resolved:" + type);
    }
}
