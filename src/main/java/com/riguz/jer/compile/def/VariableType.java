package com.riguz.jer.compile.def;

import java.util.Objects;

public class VariableType {
    private final String baseType;
    private final int arrayDimensions;

    public VariableType(String baseType) {
        this(baseType, 0);
    }

    public VariableType(String baseType, int arrayDimensions) {
        this.baseType = baseType;
        this.arrayDimensions = arrayDimensions;
    }

    public boolean isArray() {
        return arrayDimensions > 0;
    }

    public String getBaseType() {
        return baseType;
    }

    public int getArrayDimensions() {
        return arrayDimensions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableType that = (VariableType) o;
        return arrayDimensions == that.arrayDimensions &&
                Objects.equals(baseType, that.baseType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseType, arrayDimensions);
    }
}
