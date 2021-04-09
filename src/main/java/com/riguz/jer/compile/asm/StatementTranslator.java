package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.def.Statement;
import com.riguz.jer.compile.def.statement.ProcessStatement;
import com.riguz.jer.compile.exception.CompileException;
import org.objectweb.asm.tree.InsnList;

public class StatementTranslator {
    private final Scope scope;

    public StatementTranslator(Scope scope) {
        this.scope = scope;
    }

    public void translate(Statement statement, InsnList instructions) {
        if (statement instanceof ProcessStatement) {
            ProcessStatement p = (ProcessStatement) statement;
            String processName = p.getName();
            String processClass = resolveProcessProvider(processName);
            System.out.println(processClass);
        }
    }

    private String resolveProcessProvider(String method) {
        // found process in current script
//        if (scope.getScript()
//                .getProcesses()
//                .stream()
//                .anyMatch(p -> p.getName().equals(method))) {
//            return getScriptDefaultClass(scope.getScript());
//        }
        // try to find from imported classes
        scope.getScript().getImportedTypes();
        throw new CompileException("Process not found:" + method);
    }

//    private String resolveImportedProcess(String method) {
//        scope.getContext().getSources()
//                .stream()
//                .filter(script -> !script.getPath().equals(scope.getScript().getPath()))
//                .filter(script -> script.getProcesses().stream()
//                )
//    }
}
