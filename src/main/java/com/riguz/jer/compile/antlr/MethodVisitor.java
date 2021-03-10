package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Method;

import java.util.List;
import java.util.stream.Collectors;

public class MethodVisitor extends JerParserBaseVisitor<Method> {
    @Override
    public Method visitMethodDeclaration(JerParser.MethodDeclarationContext ctx) {
        JerParser.MethodSignatureContext signature = ctx.methodSignature();
        String name = signature.IDENTIFIER().getText();
        List<Method.Argument> arguments = signature.formalParameters().formalParameter()
                .stream()
                .map(p -> new Method.Argument(p.IDENTIFIER().getText(), p.type().getText()))
                .collect(Collectors.toList());
        String returnType = signature.functionReturnType() == null ? null : signature.functionReturnType().type().getText();
        return new Method(name, arguments, returnType);
    }
}
