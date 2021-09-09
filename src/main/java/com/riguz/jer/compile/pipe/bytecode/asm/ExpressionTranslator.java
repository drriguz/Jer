package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.expression.Literal;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharUtils;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.InstructionAdapter;

public class ExpressionTranslator {
    public void translate(Expression expression, MethodVisitor methodVisitor) {
        InstructionAdapter instructionAdapter = new InstructionAdapter(methodVisitor);

        if (expression instanceof Literal) {
            Literal literal = (Literal) expression;
            switch (literal.getType()) {
                case BOOL: {
                    instructionAdapter.iconst(BooleanUtils.toInteger(literal.asBoolean()));
                    instructionAdapter.invokestatic(
                            "java/lang/Boolean",
                            "valueOf",
                            "(Z)Ljava/lang/Boolean",
                            false
                    );
                    break;
                }
                case CHAR: {
                    instructionAdapter.iconst(CharUtils.toIntValue(literal.asChar()));
                    instructionAdapter.invokestatic(
                            "java/lang/Character",
                            "valueOf",
                            "(C)Ljava/lang/Character;",
                            false
                    );
                    break;
                }
                case STRING: {
                    instructionAdapter.visitLdcInsn(literal.asString());
                    break;
                }
                case DECIMAL: {
                    // todo: handle different type of numbers
                    
                }
                default:
                    break;
            }
        }
    }
}
