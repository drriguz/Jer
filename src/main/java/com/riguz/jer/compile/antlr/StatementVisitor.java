package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Statement;

import java.util.List;

public class StatementVisitor extends JerParserBaseVisitor<Statement> {
    @Override
    public Statement visitVariableDeclaration(JerParser.VariableDeclarationContext ctx) {
        return super.visitVariableDeclaration(ctx);
    }
}
