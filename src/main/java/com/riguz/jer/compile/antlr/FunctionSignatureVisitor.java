package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.FunctionSignatureContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.FunctionSignature;
import com.riguz.jer.compile.def.Parameter;

import java.util.Collections;
import java.util.List;

public class FunctionSignatureVisitor extends JerParserBaseVisitor<FunctionSignature> {
    private final FormalParametersVisitor parametersVisitor = new FormalParametersVisitor();
    private final VariableTypeVisitor variableTypeVisitor = new VariableTypeVisitor();

    @Override
    public FunctionSignature visitFunctionSignature(FunctionSignatureContext ctx) {
        final List<Parameter> parameters = ctx.formalParameters() == null ?
                Collections.emptyList() :
                ctx.formalParameters().accept(parametersVisitor);
        return new FunctionSignature(ctx.IDENTIFIER().getText(),
                parameters,
                ctx.type().accept(variableTypeVisitor));
    }
}
