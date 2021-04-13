package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.def.Process;
import com.riguz.jer.compile.pipe.pre.ClassDefinition;
import com.riguz.jer.compile.pipe.pre.ConstClassDefinition;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
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
            String processName,
            int argumentCount) {
        ResolvedType self = ResolvedType.internal(scope.getFullName(), scope);
        List<ResolvedType> importedTypes = scope.getImportedClasses()
                .values()
                .stream()
                .map(typeResolver::resolveType)
                .collect(Collectors.toList());

        return Stream.concat(Stream.of(self), importedTypes.stream())
                .map(t -> resolveCandidateProcess(t, processName, argumentCount))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<ResolvedMethod> resolveCandidateProcess(
            ResolvedType type,
            String processName,
            int argumentCount) {
        if (!type.isExternal())
            return getProcessesFromInternalType(type, processName, argumentCount);
        else
            return getStaticMethodsFromExternalClass(type, processName, argumentCount);
    }

    private List<ResolvedMethod> getProcessesFromInternalType(
            ResolvedType type,
            String processName,
            int argumentCount) {
        ClassDefinition classDefinition = type.getClassDefinition();
        if (classDefinition instanceof ConstClassDefinition) {
            ConstClassDefinition d = (ConstClassDefinition) classDefinition;
            return d.getProcesses().stream()
                    .filter(p -> p.getName().equals(processName) &&
                            p.getFormalParameters().size() == argumentCount)
                    .map(p -> convert(classDefinition, p))
                    .collect(Collectors.toList());
        }
        // todo:
        // else if (classDefinition instanceof AbstractClassDefinition) {
        // } else if (classDefinition instanceof TypeClassDefinition) {
        // }
        else
            throw new IllegalArgumentException("Unexpected class definition");
    }

    private List<ResolvedMethod> getStaticMethodsFromExternalClass(
            ResolvedType type,
            String name,
            int argumentCount) {
        return Stream.of(type.getExternalClass().getMethods())
                .filter(m -> Modifier.isStatic(m.getModifiers()))
                .filter(m -> m.getName().equals(name) && m.getParameterCount() == argumentCount)
                .map(m -> convert(type, m))
                .collect(Collectors.toList());
    }

    private ResolvedMethod convert(ResolvedType owner, Method javaMethod) {
        List<ResolvedParameter> parameters = Stream.of(javaMethod.getParameterTypes())
                .map(typeResolver::resolveExternalType)
                .map(r -> new ResolvedParameter(null, r)) // no argument name for external class
                .collect(Collectors.toList());
        return new ResolvedMethod(owner, javaMethod.getName(), parameters);
    }

    private ResolvedMethod convert(ClassDefinition scope, Process process) {
        ResolvedType owner = ResolvedType.internal(scope.getFullName(), scope);

        List<ResolvedParameter> parameters = process.getFormalParameters()
                .stream()
                .map(p -> new ResolvedParameter(p.getName(), typeResolver.resolveType(scope, p.getType())))
                .collect(Collectors.toList());

        return new ResolvedMethod(owner, process.getName(), parameters);
    }
}
