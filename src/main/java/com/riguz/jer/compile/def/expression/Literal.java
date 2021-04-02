package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.Expression;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Objects;

public class Literal extends Expression {
    private final String value;

    public Literal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Byte asByte() {
        return Byte.parseByte(value);
    }

    public Character asChar() {
        String escaped = StringEscapeUtils.unescapeJava(value);
        if (escaped.length() != 1)
            throw new IllegalArgumentException("Invalid character:" + value);
        return escaped.charAt(0);
    }

    public boolean asBoolean() {
        return Boolean.parseBoolean(value);
    }

    public short asShort() {
        return Short.parseShort(value);
    }

    public int asInteger() {
        return Integer.parseInt(value);
    }

    public long asLong() {
        return Long.parseLong(value);
    }

    public float asFloat() {
        return Float.parseFloat(value);
    }

    public double asDouble() {
        return Double.parseDouble(value);
    }

    public String asString() {
        return StringEscapeUtils.unescapeJava(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Literal literal = (Literal) o;
        return Objects.equals(value, literal.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
