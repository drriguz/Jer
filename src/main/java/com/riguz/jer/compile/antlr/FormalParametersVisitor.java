package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.FormalParametersContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Parameter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FormalParametersVisitor extends JerParserBaseVisitor<List<Parameter>> {
    @Override
    public List<Parameter> visitFormalParameters(FormalParametersContext ctx) {
        return ctx.formalParameter()
                .stream()
                .map(p -> new Parameter(p.IDENTIFIER().getText(), p.type().getText()))
                .collect(Collectors.toList());
    }
}
