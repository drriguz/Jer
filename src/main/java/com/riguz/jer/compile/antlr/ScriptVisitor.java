package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.*;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Abstract;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.statement.VariableDeclaration;
import com.riguz.jer.compile.def.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ScriptVisitor extends JerParserBaseVisitor<Script> {
    private final String fileName;
    private final String packageName;
    private final VariableDeclarationVisitor variableDeclarationVisitor = new VariableDeclarationVisitor();
    private final ProcessVisitor processVisitor = new ProcessVisitor();
    private final AbstractVisitor abstractVisitor = new AbstractVisitor();

    public ScriptVisitor(String fileName, String packageName) {
        this.fileName = fileName;
        this.packageName = packageName;
    }

    @Override
    public Script visitCompilationUint(CompilationUintContext ctx) {
        List<String> importedTypes = ctx.importedType().stream()
                .map(i -> i.fullPath().getText())
                .collect(Collectors.toList());

        List<VariableDeclaration> constants = new ArrayList<>();
        List<Process> processes = new ArrayList<>();
        List<Abstract> abstracts = new ArrayList<>();

        ctx.declaration().forEach(declarationContext -> {
            if (declarationContext.constantDeclaration() != null)
                constants.add(declarationContext.constantDeclaration().accept(variableDeclarationVisitor));
            else if (declarationContext.processDeclaration() != null)
                processes.add(declarationContext.processDeclaration().accept(processVisitor));
            else if (declarationContext.abstractDeclaration() != null)
                abstracts.add(declarationContext.abstractDeclaration().accept(abstractVisitor));
        });

        return new Script(fileName,
                packageName,
                importedTypes,
                constants,
                processes,
                abstracts,
                Collections.emptyList());
    }
}
