package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.pipe.pre.ClassDefinition;

public class ResolvedType {
    private final String className;
    private final boolean isExternal;
    private final int arrayDimensions;
    private final Class<?> externalClass;
    private final ClassDefinition classDefinition;

    private ResolvedType(String className,
                         boolean isExternal,
                         int arrayDimensions,
                         Class<?> externalClass,
                         ClassDefinition classDefinition) {
        this.className = className;
        this.isExternal = isExternal;
        this.arrayDimensions = arrayDimensions;
        this.externalClass = externalClass;
        this.classDefinition = classDefinition;
    }

    public ResolvedType toArray(int arrayDimensions) {
        return new ResolvedType(className, isExternal, arrayDimensions, externalClass, classDefinition);
    }

    public static ResolvedType internal(String className, ClassDefinition classDefinition) {
        return new ResolvedType(className, false, 0, null, classDefinition);
    }

    public static ResolvedType internalArray(ResolvedType baseType, ClassDefinition classDefinition, int arrayDimensions) {
        return new ResolvedType(baseType.className, baseType.isExternal, arrayDimensions, null, classDefinition);
    }

    public static ResolvedType external(String className, Class<?> externalClass) {
        return new ResolvedType(className, true, 0, externalClass, null);
    }

    public static ResolvedType externalArray(String className, Class<?> externalClass, int arrayDimensions) {
        return new ResolvedType(className, true, arrayDimensions, externalClass, null);
    }

    public int getArrayDimensions() {
        return arrayDimensions;
    }

    public Class<?> getExternalClass() {
        return externalClass;
    }

    public ClassDefinition getClassDefinition() {
        return classDefinition;
    }

    public String getDescriptor() {
        return String.format("%sL%s;", "[".repeat(arrayDimensions), className);
    }

    public String getClassName() {
        return className;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public boolean isArray() {
        return arrayDimensions > 0;
    }
}
