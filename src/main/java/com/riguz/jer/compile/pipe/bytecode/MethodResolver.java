package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.pipe.pre.ClassDefinition;
import com.riguz.jer.compile.pipe.pre.DefaultClassDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MethodResolver {
    private final Context context;
    private final TypeResolver typeResolver;

    public MethodResolver(Context context) {
        this.context = context;
        this.typeResolver = new TypeResolver(context);
    }

    // todo: impl this
    public List<Method> resolveCandidateProcess(
            ClassDefinition scope,
            String processName) {
        List<Method> candidates = new ArrayList<>();
        if (scope instanceof DefaultClassDefinition) {
            DefaultClassDefinition d = (DefaultClassDefinition) scope;
//            d.getProcesses().stream()
//                    .filter(p -> p.getName().equals(processName))
//                    .map(p -> new Method(d.getFullName(), p.getName(), p.))
//                    .collect(Collectors.toList());
        }
        return candidates;
    }
}
