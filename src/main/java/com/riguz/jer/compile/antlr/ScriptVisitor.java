package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.*;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.statement.VariableDeclaration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ScriptVisitor extends JerParserBaseVisitor<Script> {
    private final String fileName;
    private final String packageName;
    private final VariableDeclarationVisitor variableDeclarationVisitor = new VariableDeclarationVisitor();

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
        ctx.declaration().forEach(declarationContext -> {
            if (declarationContext.constantDeclaration() != null)
                constants.add(declarationContext.constantDeclaration().accept(variableDeclarationVisitor));
        });

        return new Script(fileName,
                packageName,
                importedTypes,
                constants,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());
    }

}
