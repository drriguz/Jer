package com.riguz.jer.compile.pipe.pre;

import com.riguz.jer.compile.def.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TypeClassDefinition extends ClassDefinition {
    private final List<Parameter> properties;
    private final List<Constructor> constructors;
    private final List<Function> functions;

    public TypeClassDefinition(String className,
                               String packageName,
                               Map<String, String> importedClasses,
                               List<Parameter> properties,
                               List<Constructor> constructors,
                               List<Function> functions) {
        super(className, packageName, importedClasses);
        this.properties = properties;
        this.constructors = constructors;
        this.functions = functions;
    }

    public static TypeClassDefinition from(Script script, Type t) {
        Map<String, String> importedTypes = script.getImportedTypes()
                .stream()
                .collect(Collectors.toMap(p -> p.substring(p.lastIndexOf('/')), p -> p));

        return new TypeClassDefinition(
                t.getName(),
                script.getPackageName(),
                importedTypes,
                t.getProperties(),
                t.getConstructors(),
                t.getFunctions()
        );
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
