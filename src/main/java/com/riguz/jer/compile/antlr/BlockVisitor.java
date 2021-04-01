package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.BlockContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Block;
import com.riguz.jer.compile.def.Statement;

import java.util.List;
import java.util.stream.Collectors;

public class BlockVisitor extends JerParserBaseVisitor<Block> {
    private final StatementVisitor statementVisitor = new StatementVisitor();

    @Override
    public Block visitBlock(BlockContext ctx) {
        List<Statement> statements = ctx.statement()
                .stream()
                .map(s -> s.accept(statementVisitor))
                .collect(Collectors.toList());
        return new Block(statements);
    }
}
