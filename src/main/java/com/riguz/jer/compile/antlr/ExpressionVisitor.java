package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.expression.Literal;

public class ExpressionVisitor extends JerParserBaseVisitor<Expression> {
    @Override
    public Expression visitExpression(JerParser.ExpressionContext ctx) {
        if (ctx.literal() != null) {
            if (ctx.literal().FLOAT_LITERAL() != null)
                return new Literal(ctx.literal().getText());
        }
        return null;
    }
}
