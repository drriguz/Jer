package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropertyValue that = (PropertyValue) o;
        return Objects.equals(instance, that.instance) &&
                Objects.equals(propertyName, that.propertyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instance, propertyName);
    }
}
