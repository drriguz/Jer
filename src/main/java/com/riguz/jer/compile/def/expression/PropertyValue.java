package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

public class PropertyValue extends Expression {
    private final Expression instance;
    private final String propertyName;

    public PropertyValue(Expression instance, String propertyName) {
        this.instance = instance;
        this.propertyName = propertyName;
    }

    public Expression getInstance() {
        return instance;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
