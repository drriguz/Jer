package com.riguz.jer.compile.pipe.pre;

import com.riguz.jer.compile.def.Process;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.statement.VariableDeclaration;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultClassDefinition extends ClassDefinition {
    private final List<VariableDeclaration> constants;
    private final List<Process> processes;

    public DefaultClassDefinition(String className,
                                  String packageName,
                                  Map<String, String> importedClasses,
                                  List<VariableDeclaration> constants,
                                  List<Process> processes) {
        super(className, packageName, importedClasses);
        this.constants = Collections.unmodifiableList(constants);
        this.processes = Collections.unmodifiableList(processes);
    }

    public static DefaultClassDefinition from(Script script) {
        Map<String, String> importedTypes = script.getImportedTypes()
                .stream()
                .collect(Collectors.toMap(p -> p.substring(p.lastIndexOf('/')), p -> p));

        return new DefaultClassDefinition(
                script.getFileName().replace(".jer", ""),
                script.getPackageName(),
                importedTypes,
                script.getConstants(),
                script.getProcesses()
        );
    }

    public List<VariableDeclaration> getConstants() {
        return constants;
    }

    public List<Process> getProcesses() {
        return processes;
    }
}
