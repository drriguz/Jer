package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.CompilationUintContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Abstract;
import com.riguz.jer.compile.def.Process;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.Type;
import com.riguz.jer.compile.def.statement.VariableDeclaration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ScriptVisitor extends JerParserBaseVisitor<Script> {
    private final String fileName;
    private final String packageName;
    private final VariableDeclarationVisitor variableDeclarationVisitor = new VariableDeclarationVisitor();
    private final ProcessVisitor processVisitor = new ProcessVisitor();
    private final AbstractVisitor abstractVisitor = new AbstractVisitor();
    private final TypeVisitor typeVisitor = new TypeVisitor();

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
        List<Type> types = new ArrayList<>();

        ctx.declaration().forEach(c -> {
            if (c.constantDeclaration() != null)
                constants.add(c.constantDeclaration().accept(variableDeclarationVisitor));
            else if (c.processDeclaration() != null)
                processes.add(c.processDeclaration().accept(processVisitor));
            else if (c.abstractDeclaration() != null)
                abstracts.add(c.abstractDeclaration().accept(abstractVisitor));
            else if (c.typeDeclaration() != null)
                types.add(c.typeDeclaration().accept(typeVisitor));
            else
                throw new IllegalArgumentException("Unexpected declaration:" + c.getText());
        });

        return new Script(fileName,
                packageName,
                importedTypes,
                constants,
                processes,
                abstracts,
                types);
    }
}
