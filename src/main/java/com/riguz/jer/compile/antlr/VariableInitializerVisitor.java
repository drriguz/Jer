package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.VariableInitializerContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.statement.VariableDeclaration.VariableInitializer;

import java.util.List;
import java.util.stream.Collectors;

public class VariableInitializerVisitor
        extends JerParserBaseVisitor<VariableInitializer> {
    private final ExpressionVisitor expressionVisitor = new ExpressionVisitor();

    @Override
    public VariableInitializer visitVariableInitializer(VariableInitializerContext ctx) {
        if (ctx.expression() != null) {
            Expression v = ctx.expression().accept(expressionVisitor);
            return VariableInitializer.of(v);
        } else if (ctx.arrayInitializer() != null) {
            List<VariableInitializer> arrayValues = ctx.arrayInitializer().variableInitializer()
                    .stream()
                    .map(this::visitVariableInitializer)
                    .collect(Collectors.toList());
            return VariableInitializer.of(arrayValues);
        }
        throw new RuntimeException("Unexpected variable initializer");
    }

}
