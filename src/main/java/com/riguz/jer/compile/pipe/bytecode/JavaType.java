package com.riguz.jer.compile.pipe.bytecode;

public class JavaType {
    private final String className;
    private final boolean isExternal;
    private final boolean isArray;
    private final String arrayDescriptor; // [[[

    private JavaType(String className,
                     boolean isExternal,
                     boolean isArray,
                     String arrayDescriptor) {
        this.className = className;
        this.isExternal = isExternal;
        this.isArray = isArray;
        this.arrayDescriptor = arrayDescriptor;
    }

    public static JavaType of(String className, boolean isExternal) {
        return new JavaType(className, isExternal, false, null);
    }

    public static JavaType of(String className, boolean isExternal, String arrayDescriptor) {
        return new JavaType(className, isExternal, true, arrayDescriptor);
    }

    public static JavaType of(JavaType baseType, String arrayDescriptor) {
        return new JavaType(baseType.className, baseType.isExternal, true, arrayDescriptor);
    }

    public String getDescriptor() {
        return isArray ?
                String.format("%sL%s;", arrayDescriptor, className) :
                String.format("L%s;", className);
    }

    public String getClassName() {
        return className;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public boolean isArray() {
        return isArray;
    }

    public String getArrayDescriptor() {
        return arrayDescriptor;
    }
}
