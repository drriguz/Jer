package com.riguz.jer.compile.def;

import com.riguz.jer.compile.def.statement.VariableDeclaration;

import java.util.Collections;
import java.util.List;

public class Script {
    private final String fileName;
    private final String packageName;
    private final List<String> importedTypes;
    private final List<VariableDeclaration> constants;
    private final List<Process> processes;
    private final List<Abstract> abstracts;
    private final List<Type> types;

    public Script(String fileName,
                  String packageName,
                  List<String> importedTypes,
                  List<VariableDeclaration> constants,
                  List<Process> processes,
                  List<Abstract> abstracts,
                  List<Type> types) {
        this.fileName = fileName;
        this.packageName = packageName;
        this.importedTypes = Collections.unmodifiableList(importedTypes);
        this.constants = Collections.unmodifiableList(constants);
        this.processes = Collections.unmodifiableList(processes);
        this.abstracts = Collections.unmodifiableList(abstracts);
        this.types = Collections.unmodifiableList(types);
    }

    public String getFileName() {
        return fileName;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<String> getImportedTypes() {
        return importedTypes;
    }

    public List<VariableDeclaration> getConstants() {
        return constants;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public List<Abstract> getAbstracts() {
        return abstracts;
    }

    public List<Type> getTypes() {
        return types;
    }
}
