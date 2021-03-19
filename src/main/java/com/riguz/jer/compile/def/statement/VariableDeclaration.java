package com.riguz.jer.compile.def.statement;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;

import java.util.Collections;
import java.util.List;

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

        public <T extends Expression> T getValue() {
            return (T) value;
        }
    }
}
