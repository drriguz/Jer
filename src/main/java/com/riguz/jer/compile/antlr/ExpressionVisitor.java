package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser;
import com.riguz.jer.antlr.generated.JerParser.ObjectCreationContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.expression.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ExpressionVisitor extends JerParserBaseVisitor<Expression> {
    @Override
    public Expression visitExpression(JerParser.ExpressionContext ctx) {
        if (ctx.literal() != null) {
            return visitLiteral(ctx.literal());
        } else if (ctx.functionCall != null) {
            Expression instance = visitExpression(ctx.expression());
            return new FunctionCall(instance,
                    ctx.IDENTIFIER().getText(),
                    extractArguments(ctx.expressionList())
            );
        } else if (ctx.propertyCall != null) {
            Expression instance = visitExpression(ctx.expression());
            return new PropertyValue(instance, ctx.IDENTIFIER().getText());
        } else if (ctx.variableReference() != null) {
            if (ctx.variableReference().THIS() != null)
                return new VariableReference("this", true);
            else
                return new VariableReference(ctx.variableReference().IDENTIFIER().getText(), false);
        } else if (ctx.nested != null) {
            return visitExpression(ctx.expression());
        } else if (ctx.objectCreation() != null) {
            return visitObjectCreation(ctx.objectCreation());
        }
        throw new IllegalArgumentException("Unexpected expression");
    }

    @Override
    public Expression visitObjectCreation(ObjectCreationContext ctx) {
        return new ObjectCreation(ctx.TYPE_NAME().getText(),
                extractArguments(ctx.expressionList()));
    }

    private List<Expression> extractArguments(JerParser.ExpressionListContext ctx) {
        if (ctx == null)
            return Collections.emptyList();
        return ctx.expression()
                .stream()
                .map(e -> e.accept(this))
                .collect(Collectors.toList());
    }

    @Override
    public Expression visitLiteral(JerParser.LiteralContext ctx) {
        if (ctx.FLOAT_LITERAL() != null ||
                ctx.DECIMAL_LITERAL() != null ||
                ctx.BOOL_LITERAL() != null)
            return new Literal(ctx.getText());
        else if (ctx.CHAR_LITERAL() != null ||
                ctx.STRING_LITERAL() != null)
            return new Literal(removeQuotes(ctx.getText()));
        else
            throw new IllegalArgumentException("Unexpected literal");
    }

    private static String removeQuotes(String quoted) {
        return quoted.substring(1, quoted.length() - 1);
    }
}
