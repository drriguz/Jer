package com.riguz.jer.compile.pipe.pre;

import com.riguz.jer.compile.def.Abstract;
import com.riguz.jer.compile.def.FunctionSignature;
import com.riguz.jer.compile.def.Parameter;
import com.riguz.jer.compile.def.Script;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AbstractClassDefinition extends ClassDefinition {
    private final List<Parameter> properties;
    private final List<FunctionSignature> functionSignatures;

    public AbstractClassDefinition(String className,
                                   String packageName,
                                   Map<String, String> importedClasses,
                                   List<Parameter> properties,
                                   List<FunctionSignature> functionSignatures) {
        super(className, packageName, importedClasses);
        this.properties = properties;
        this.functionSignatures = functionSignatures;
    }

    public static AbstractClassDefinition from(Script script, Abstract a) {
        Map<String, String> importedTypes = script.getImportedTypes()
                .stream()
                .collect(Collectors.toMap(p -> p.substring(p.lastIndexOf('/')), p -> p));

        return new AbstractClassDefinition(
                a.getName(),
                script.getPackageName(),
                importedTypes,
                a.getProperties(),
                a.getFunctionSignatures()
        );
    }

    public List<Parameter> getProperties() {
        return properties;
    }

    public List<FunctionSignature> getFunctionSignatures() {
        return functionSignatures;
    }
}
