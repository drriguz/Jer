package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.statement.VariableDeclaration;
import com.riguz.jer.compile.def.statement.VariableDeclaration.VariableInitializer;

public class VariableDeclarationVisitor extends JerParserBaseVisitor<VariableDeclaration> {
    private final VariableInitializerVisitor variableInitializerVisitor = new VariableInitializerVisitor();

    @Override
    public VariableDeclaration visitVariableDeclaration(JerParser.VariableDeclarationContext ctx) {
        VariableInitializer variableInitializer = ctx.variableInitializer().accept(variableInitializerVisitor);
        return new VariableDeclaration(ctx.IDENTIFIER().getText(),
                ctx.type().getText(),
                variableInitializer);
    }

}
