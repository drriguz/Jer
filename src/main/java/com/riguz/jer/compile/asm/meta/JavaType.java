package com.riguz.jer.compile.asm.meta;

public class JavaType {
    private final String fullQualifiedName;
    private final String className;
    private final String packageName;
    private final boolean isExternal;
    private final boolean isDefault;

    public JavaType(String className,
                    String packageName,
                    boolean isExternal,
                    boolean isDefault) {
        this.fullQualifiedName = packageName + "/" + className;
        this.className = className;
        this.packageName = packageName;
        this.isExternal = isExternal;
        this.isDefault = isDefault;
    }

    public String getFullQualifiedName() {
        return fullQualifiedName;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public boolean isExternal() {
        return isExternal;
    }
}
