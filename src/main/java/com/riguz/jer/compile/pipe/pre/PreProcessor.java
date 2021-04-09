package com.riguz.jer.compile.pipe.pre;

import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PreProcessor {
    public List<ClassDefinition> process(List<Script> sources) throws CompileException {
        return sources.stream()
                .map(this::process)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ClassDefinition> process(Script script) {
        List<ClassDefinition> classes = new ArrayList<>();
        if (!script.getConstants().isEmpty() ||
                !script.getProcesses().isEmpty()) {
            classes.add(DefaultClassDefinition.from(script));
        }
        List<AbstractClassDefinition> abstracts = script.getAbstracts()
                .stream()
                .map(a -> AbstractClassDefinition.from(script, a))
                .collect(Collectors.toList());

        List<TypeClassDefinition> types = script.getTypes()
                .stream()
                .map(t -> TypeClassDefinition.from(script, t))
                .collect(Collectors.toList());

        classes.addAll(abstracts);
        classes.addAll(types);
        return classes;
    }
}
