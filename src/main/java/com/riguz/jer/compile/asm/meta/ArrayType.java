package com.riguz.jer.compile.asm.meta;

public class ArrayType extends JavaType {
    private final String arrayPrefix;

    public ArrayType(String className,
                     String packageName,
                     boolean isExternal,
                     boolean isDefault,
                     String arrayPrefix,
                     String arrayPrefix1) {
        super(className, packageName, isExternal, isDefault);
        this.arrayPrefix = arrayPrefix1;
    }

    public String getArrayPrefix() {
        return arrayPrefix;
    }
}
