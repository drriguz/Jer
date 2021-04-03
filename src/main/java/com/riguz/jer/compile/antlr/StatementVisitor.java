package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.*;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;
import com.riguz.jer.compile.def.statement.*;

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
        else if (ctx.selectionStatement() != null)
            return visitSelectionStatement(ctx.selectionStatement());
        else if (ctx.loopStatement() != null)
            return visitLoopStatement(ctx.loopStatement());
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

    @Override
    public Statement visitSelectionStatement(SelectionStatementContext ctx) {
        return new SelectionStatement(
                ctx.expression().accept(expressionVisitor),
                visitNestedBlock(ctx.ifStatement),
                ctx.ELSE() == null ?
                        null :
                        visitNestedBlock(ctx.elseStatement)
        );
    }

    @Override
    public Statement visitNestedBlock(NestedBlockContext ctx) {
        if (ctx.singleLine != null)
            return ctx.singleLine.accept(this);
        else {
            List<Statement> statements = ctx.statement()
                    .stream()
                    .map(s -> s.accept(this))
                    .collect(Collectors.toList());
            if (statements.size() == 1)
                return statements.get(0);
            else
                return new NestedBlock(statements);
        }
    }

    @Override
    public Statement visitLoopStatement(LoopStatementContext ctx) {
        return new LoopStatement(ctx.expression().accept(expressionVisitor),
                visitNestedBlock(ctx.nestedBlock()));
    }
}
