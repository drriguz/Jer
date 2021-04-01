package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.ProcessDeclarationContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Parameter;
import com.riguz.jer.compile.def.Process;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessVisitor extends JerParserBaseVisitor<Process> {
    private final BlockVisitor blockVisitor = new BlockVisitor();

    @Override
    public Process visitProcessDeclaration(ProcessDeclarationContext ctx) {
        final List<Parameter> parameters;
        if (ctx.formalParameters() != null)
            parameters = ctx.formalParameters().formalParameter()
                    .stream()
                    .map(p -> new Parameter(p.IDENTIFIER().getText(), p.type().getText()))
                    .collect(Collectors.toList());
        else
            parameters = Collections.emptyList();
        return new Process(ctx.IDENTIFIER().getText(), parameters, ctx.block().accept(blockVisitor));
    }
}
