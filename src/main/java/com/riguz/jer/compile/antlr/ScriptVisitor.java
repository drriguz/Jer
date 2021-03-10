package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.*;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Method;
import com.riguz.jer.compile.def.Script;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScriptVisitor extends JerParserBaseVisitor<Script> {
    private final String fileName;
    private final String packageName;

    public ScriptVisitor(String fileName, String packageName) {
        this.fileName = fileName;
        this.packageName = packageName;
    }

    @Override
    public Script visitCompilationUint(CompilationUintContext ctx) {
        List<String> importedTypes = ctx.importedType().stream()
                .map(i -> i.fullPath().getText())
                .collect(Collectors.toList());

        MethodVisitor methodVisitor = new MethodVisitor();
        List<Method> methods = ctx.declaration().stream()
                .map(DeclarationContext::methodDeclaration)
                .filter(Objects::nonNull)
                .map(d -> d.accept(methodVisitor))
                .collect(Collectors.toList());
        return new Script(fileName, packageName, importedTypes, methods);
    }

}
