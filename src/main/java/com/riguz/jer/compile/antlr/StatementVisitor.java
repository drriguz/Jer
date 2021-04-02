package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.AssignStatementContext;
import com.riguz.jer.antlr.generated.JerParser.ProcessStatementContext;
import com.riguz.jer.antlr.generated.JerParser.StatementContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;
import com.riguz.jer.compile.def.statement.AssignStatement;
import com.riguz.jer.compile.def.statement.ProcessStatement;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StatementVisitor extends JerParserBaseVisitor<Statement> {
    private final VariableDeclarationVisitor variableDeclarationVisitor = new VariableDeclarationVisitor();
    private final ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    @Override
    public Statement visitStatement(StatementContext ctx) {
        if (ctx.variableDeclaration() != null)
            return ctx.variableDeclaration().accept(variableDeclarationVisitor);
        else if (ctx.assignStatement() != null)
            return visitAssignStatement(ctx.assignStatement());
        else if (ctx.processStatement() != null)
            return visitProcessStatement(ctx.processStatement());
        return null;
    }

    @Override
    public Statement visitAssignStatement(AssignStatementContext ctx) {
        return new AssignStatement(ctx.IDENTIFIER().getText(),
                ctx.expression().accept(expressionVisitor));
    }

    @Override
    public Statement visitProcessStatement(ProcessStatementContext ctx) {
        List<Expression> arguments =
                ctx.expressionList() == null ?
                        Collections.emptyList() :
                        ctx.expressionList()
                                .expression()
                                .stream()
                                .map(e -> e.accept(expressionVisitor))
                                .collect(Collectors.toList());
        return new ProcessStatement(ctx.IDENTIFIER().getText(),
                arguments);
    }
}
