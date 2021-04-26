package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.expression.Literal;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.IntInsnNode;

public class ExpressionTranslator {
    public InsnList translate(Expression expression) {
        InsnList instructions = new InsnList();
        if (expression instanceof Literal) {
            Literal literal = (Literal) expression;
            switch(literal.getType()) {
                case BOOL:
            }
        }
        return null;
    }
}
