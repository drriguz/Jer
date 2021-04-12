package com.riguz.jer.compile.pipe.bytecode;

public class JavaType {
    private final String className;
    private final boolean isExternal;
    private final int arrayDimensions;
    private final Class<?> externalClass;

    private JavaType(String className,
                     boolean isExternal,
                     int arrayDimensions,
                     Class<?> externalClass) {
        this.className = className;
        this.isExternal = isExternal;
        this.arrayDimensions = arrayDimensions;
        this.externalClass = externalClass;
    }

    public JavaType toArray(int arrayDimensions) {
        return new JavaType(className, isExternal, arrayDimensions, externalClass);
    }

    public static JavaType internal(String className) {
        return new JavaType(className, false, 0, null);
    }

    public static JavaType internalArray(JavaType baseType, int arrayDimensions) {
        return new JavaType(baseType.className, baseType.isExternal, arrayDimensions, null);
    }

    public static JavaType external(String className, Class<?> externalClass) {
        return new JavaType(className, true, 0, externalClass);
    }

    public static JavaType externalArray(String className, Class<?> externalClass, int arrayDimensions) {
        return new JavaType(className, true, arrayDimensions, externalClass);
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
