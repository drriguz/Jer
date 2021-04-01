package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.AssignStatementContext;
import com.riguz.jer.antlr.generated.JerParser.StatementContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Statement;
import com.riguz.jer.compile.def.statement.AssignStatement;

public class StatementVisitor extends JerParserBaseVisitor<Statement> {
    private final VariableDeclarationVisitor variableDeclarationVisitor = new VariableDeclarationVisitor();
    private final ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    @Override
    public Statement visitStatement(StatementContext ctx) {
        if (ctx.variableDeclaration() != null)
            return ctx.variableDeclaration().accept(variableDeclarationVisitor);
        else if (ctx.assignStatement() != null)
            return visitAssignStatement(ctx.assignStatement());
        return null;
    }

    @Override
    public Statement visitAssignStatement(AssignStatementContext ctx) {
        return new AssignStatement(ctx.IDENTIFIER().getText(),
                ctx.expression().accept(expressionVisitor));
    }
}
