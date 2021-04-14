package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.expression.Literal;

public class ExpressionResolver {
    private final Context context;
    private final TypeResolver typeResolver;

    public ExpressionResolver(Context context) {
        this.context = context;
        this.typeResolver = new TypeResolver(context);
    }

    public ResolvedType resolveExpression(Expression expression) {
        if (expression instanceof Literal) {
            Literal literal = (Literal) expression;
            switch (literal.getType()) {
                case DECIMAL:
                    return typeResolver.resolveExternalType(Number.class);
                case STRING:
                    return typeResolver.resolveExternalType(String.class);
                case CHAR:
                    return typeResolver.resolveExternalType(Character.class);
                case BOOL:
                    return typeResolver.resolveExternalType(Boolean.class);
                default:
                    throw new IllegalArgumentException("Unexpected literal type");
            }
        }
        // fixme:
        throw new IllegalArgumentException("Not supported yet");
    }
}
