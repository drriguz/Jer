package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class VariableDeclaration extends Statement {
    private final String variableName;
    private final String type;
    private final VariableInitializer variableInitializer;

    public VariableDeclaration(String variableName,
                               String type,
                               VariableInitializer variableInitializer) {
        this.variableName = variableName;
        this.type = type;
        this.variableInitializer = variableInitializer;
    }

    public String getVariableName() {
        return variableName;
    }

    public String getType() {
        return type;
    }

    public VariableInitializer getVariableInitializer() {
        return variableInitializer;
    }

    public static class VariableInitializer {
        private final boolean isArray;
        private final List<VariableInitializer> arrayInitializer;
        private final Expression value;

        private VariableInitializer(boolean isArray,
                                    List<VariableInitializer> arrayInitializer,
                                    Expression value) {
            this.isArray = isArray;
            this.arrayInitializer = Collections.unmodifiableList(arrayInitializer);
            this.value = value;
        }

        public static VariableInitializer of(Expression value) {
            return new VariableInitializer(false, Collections.emptyList(), value);
        }

        public static VariableInitializer of(List<VariableInitializer> arrayInitializer) {
            return new VariableInitializer(true, arrayInitializer, null);
        }

        public boolean isArray() {
            return isArray;
        }

        public List<VariableInitializer> getArrayInitializer() {
            return arrayInitializer;
        }

        @SuppressWarnings("unchecked")
        public <T extends Expression> T getValue() {
            return (T) value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VariableInitializer that = (VariableInitializer) o;
            return isArray == that.isArray &&
                    Objects.equals(arrayInitializer, that.arrayInitializer) &&
                    Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(isArray, arrayInitializer, value);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableDeclaration that = (VariableDeclaration) o;
        return Objects.equals(variableName, that.variableName) &&
                Objects.equals(type, that.type) &&
                Objects.equals(variableInitializer, that.variableInitializer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName, type, variableInitializer);
    }
}
