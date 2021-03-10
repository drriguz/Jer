package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

public class FieldReference extends Expression {
    private final String fieldName;

    public FieldReference(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
