package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.statement.VariableDeclaration;

public class VariableDeclarationVisitor extends JerParserBaseVisitor<VariableDeclaration> {
    private final ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    @Override
    public VariableDeclaration visitVariableDeclaration(JerParser.VariableDeclarationContext ctx) {
        JerParser.VariableInitializerContext value = ctx.variableInitializer();
        VariableDeclaration.VariableInitializer variableInitializer = null;
        if (value.expression() != null) {
            Expression v = value.expression().accept(expressionVisitor);
            variableInitializer = VariableDeclaration.VariableInitializer.of(v);
        }
        return new VariableDeclaration(ctx.IDENTIFIER().getText(), ctx.type().getText(), variableInitializer);
    }
}
