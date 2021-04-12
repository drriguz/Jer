package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerParser.TypeContext;
import com.riguz.jer.antlr.generated.JerParserBaseVisitor;
import com.riguz.jer.compile.def.VariableType;

public class VariableTypeVisitor extends JerParserBaseVisitor<VariableType> {
    @Override
    public VariableType visitType(TypeContext ctx) {
        return visitType(ctx, 0);
    }

    public VariableType visitType(TypeContext ctx, int arrayDimensions) {
        if (ctx.TYPE_NAME() != null)
            return new VariableType(ctx.TYPE_NAME().getText(), arrayDimensions);
        return visitType(ctx.arrayType().type(), arrayDimensions + 1);
    }
}
