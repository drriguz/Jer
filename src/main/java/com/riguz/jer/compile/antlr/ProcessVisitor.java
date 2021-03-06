package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.ProcessDeclarationContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Parameter;
import com.riguz.jer.compile.def.Process;

import java.util.Collections;
import java.util.List;

public class ProcessVisitor extends JerParserBaseVisitor<Process> {
    private final BlockVisitor blockVisitor = new BlockVisitor();
    private final FormalParametersVisitor parametersVisitor = new FormalParametersVisitor();

    @Override
    public Process visitProcessDeclaration(ProcessDeclarationContext ctx) {
        final List<Parameter> parameters = ctx.formalParameters() == null ?
                Collections.emptyList() :
                ctx.formalParameters().accept(parametersVisitor);
        return new Process(ctx.IDENTIFIER().getText(), parameters, ctx.block().accept(blockVisitor));
    }
}
