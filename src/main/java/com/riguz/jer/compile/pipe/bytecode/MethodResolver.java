package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.def.Process;
import com.riguz.jer.compile.pipe.pre.ClassDefinition;
import com.riguz.jer.compile.pipe.pre.ConstClassDefinition;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodResolver {
    private final Context context;
    private final TypeResolver typeResolver;

    public MethodResolver(Context context) {
        this.context = context;
        this.typeResolver = new TypeResolver(context);
    }

    public List<ResolvedMethod> resolveCandidateProcess(
            ClassDefinition scope,
            String processName) {
        ResolvedType self = ResolvedType.internal(scope.getFullName(), scope);
        List<ResolvedType> importedTypes = scope.getImportedClasses()
                .values()
                .stream()
                .map(fullName -> typeResolver.resolveType(fullName))
                .collect(Collectors.toList());

        return Stream.concat(Stream.of(self), importedTypes.stream())
                .map(t -> resolveCandidateProcess(t, processName))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ResolvedMethod> resolveCandidateProcess(ResolvedType type, String processName) {
        if (!type.isExternal()) {
            ClassDefinition classDefinition = type.getClassDefinition();
            if (classDefinition instanceof ConstClassDefinition) {
                ConstClassDefinition d = (ConstClassDefinition) classDefinition;
                return d.getProcesses().stream()
                        .filter(p -> p.getName().equals(processName))
                        .map(p -> convert(classDefinition, p))
                        .collect(Collectors.toList());
            }
        }
        // todo: support external classes
        return Collections.emptyList();
    }

    private ResolvedMethod convert(ClassDefinition scope, Process process) {
        ResolvedType owner = ResolvedType.internal(scope.getFullName(), scope);

        List<ResolvedParameter> parameters = process.getFormalParameters()
                .stream()
                .map(p -> new ResolvedParameter(p.getName(), typeResolver.resolveType(scope, p.getType())))
                .collect(Collectors.toList());

        return ResolvedMethod.internal(owner, process.getName(), parameters);
    }
}
