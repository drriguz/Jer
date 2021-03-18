package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;

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
}
