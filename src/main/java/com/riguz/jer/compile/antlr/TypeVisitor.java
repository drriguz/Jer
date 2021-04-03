package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.ConstructorDeclarationContext;
import com.riguz.jer.antlr.generated.JerParser.FunctionDeclarationContext;
import com.riguz.jer.antlr.generated.JerParser.TypeDeclarationContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TypeVisitor extends JerParserBaseVisitor<Type> {
    private final FunctionSignatureVisitor functionSignatureVisitor = new FunctionSignatureVisitor();
    private final BlockVisitor blockVisitor = new BlockVisitor();
    private final FormalParametersVisitor parametersVisitor = new FormalParametersVisitor();

    @Override
    public Type visitTypeDeclaration(TypeDeclarationContext ctx) {
        List<Parameter> properties = ctx.propertySignature()
                .stream()
                .map(p -> new Parameter(p.IDENTIFIER().getText(), p.type().getText()))
                .collect(Collectors.toList());

        List<String> abstractions = visitAbstractions(ctx);

        return new Type(ctx.TYPE_NAME().getText(), abstractions, properties,
                visitConstructors(ctx),
                visitFunctions(ctx));
    }

    private List<Function> visitFunctions(TypeDeclarationContext ctx) {
        return ctx.functionDeclaration()
                .stream()
                .map(this::visitFunction)
                .collect(Collectors.toList());
    }

    private Function visitFunction(FunctionDeclarationContext ctx) {
        FunctionSignature signature = ctx.functionSignature().accept(functionSignatureVisitor);
        return new Function(signature.getName(),
                signature.getFormalParameters(),
                signature.getReturnType(),
                ctx.block().accept(blockVisitor));
    }

    private List<String> visitAbstractions(TypeDeclarationContext ctx) {
        return ctx.typeAbstractions() == null ?
                Collections.emptyList() :
                ctx.typeAbstractions().TYPE_NAME()
                        .stream()
                        .map(ParseTree::getText)
                        .collect(Collectors.toList());
    }

    private List<Constructor> visitConstructors(TypeDeclarationContext ctx) {
        return ctx.constructorDeclaration()
                .stream()
                .map(this::visitConstructor)
                .collect(Collectors.toList());
    }

    private Constructor visitConstructor(ConstructorDeclarationContext ctx) {
        List<Parameter> parameters = ctx.constructorFormalArguments() == null ?
                Collections.emptyList() :
                ctx.constructorFormalArguments().constructorFormalArgument()
                        .stream()
                        .map(c -> new Parameter(c.IDENTIFIER().getText(),
                                c.TYPE_NAME() == null ? null : c.TYPE_NAME().getText()))
                        .collect(Collectors.toList());
        return new Constructor(
                parameters,
                ctx.block().accept(blockVisitor)
        );
    }
}
