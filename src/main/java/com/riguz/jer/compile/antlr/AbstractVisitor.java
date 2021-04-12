package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.AbstractDeclarationContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Abstract;
import com.riguz.jer.compile.def.FunctionSignature;
import com.riguz.jer.compile.def.Parameter;

import java.util.List;
import java.util.stream.Collectors;

public class AbstractVisitor extends JerParserBaseVisitor<Abstract> {
    private final FunctionSignatureVisitor functionSignatureVisitor = new FunctionSignatureVisitor();
    private final VariableTypeVisitor variableTypeVisitor = new VariableTypeVisitor();

    @Override
    public Abstract visitAbstractDeclaration(AbstractDeclarationContext ctx) {
        List<Parameter> properties = ctx.propertySignature()
                .stream()
                .map(p -> new Parameter(p.IDENTIFIER().getText(), p.type().accept(variableTypeVisitor)))
                .collect(Collectors.toList());
        List<FunctionSignature> functions = ctx.functionSignature()
                .stream()
                .map(f -> f.accept(functionSignatureVisitor))
                .collect(Collectors.toList());
        return new Abstract(
                ctx.TYPE_NAME().getText(),
                properties,
                functions
        );
    }

}
