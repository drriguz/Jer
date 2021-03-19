package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.expression.Literal;

public class ExpressionVisitor extends JerParserBaseVisitor<Expression> {
    @Override
    public Expression visitExpression(JerParser.ExpressionContext ctx) {
        if (ctx.literal() != null) {
            return visitLiteral(ctx.literal());
        }
        return null;
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
