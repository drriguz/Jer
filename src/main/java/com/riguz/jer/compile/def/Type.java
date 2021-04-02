package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Type {
    private final String name;
    private final List<String> abstractions;
    private final List<Parameter> properties;
    private final List<Constructor> constructors;
    private final List<Function> functions;

    public Type(String name,
                List<String> abstractions,
                List<Parameter> properties,
                List<Constructor> constructors,
                List<Function> functions) {
        this.name = name;
        this.abstractions = Collections.unmodifiableList(abstractions);
        this.properties = Collections.unmodifiableList(properties);
        this.constructors = Collections.unmodifiableList(constructors);
        this.functions = Collections.unmodifiableList(functions);
    }

    public String getName() {
        return name;
    }

    public List<String> getAbstractions() {
        return abstractions;
    }

    public List<Parameter> getProperties() {
        return properties;
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(name, type.name) &&
                Objects.equals(abstractions, type.abstractions) &&
                Objects.equals(properties, type.properties) &&
                Objects.equals(constructors, type.constructors) &&
                Objects.equals(functions, type.functions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, abstractions, properties, constructors, functions);
    }
}
