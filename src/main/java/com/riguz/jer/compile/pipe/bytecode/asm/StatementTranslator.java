package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Statement;
import com.riguz.jer.compile.def.statement.ProcessStatement;
import com.riguz.jer.compile.exception.CompileException;
import com.riguz.jer.compile.pipe.bytecode.*;
import com.riguz.jer.compile.pipe.pre.ClassDefinition;
import org.objectweb.asm.MethodVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.objectweb.asm.Opcodes.INVOKESTATIC;

public class StatementTranslator {
    private final Context context;
    private final ClassDefinition providerClass;
    private final TypeResolver typeResolver;
    private final MethodResolver methodResolver;
    private final ExpressionResolver expressionResolver;
    private final ExpressionTranslator expressionTranslator;

    public StatementTranslator(Context context, ClassDefinition providerClass) {
        this.context = context;
        this.providerClass = providerClass;
        this.typeResolver = new TypeResolver(context);
        this.methodResolver = new MethodResolver(context);
        this.expressionResolver = new ExpressionResolver(context);
        this.expressionTranslator = new ExpressionTranslator();
    }

    public void translate(Statement statement, MethodVisitor methodVisitor) {
        if (statement instanceof ProcessStatement) {
            ProcessStatement p = (ProcessStatement) statement;
            translate(p, methodVisitor);
        }
    }

    private void translate(ProcessStatement processStatement, MethodVisitor methodVisitor) {
        List<ResolvedMethod> candidates = new ArrayList<>(methodResolver
                .resolveCandidateProcess(providerClass,
                        processStatement.getName(),
                        processStatement.getArguments().size()))
                .stream()
                .filter(p -> isApplicable(p, processStatement.getArguments()))
                .collect(Collectors.toList());
        if (candidates.isEmpty())
            throw new CompileException("No applicable method found:" + processStatement.getName());
        if (candidates.size() > 1)
            throw new CompileException("Multiple candidates found:" + processStatement.getName());

        ResolvedMethod method = candidates.get(0);
        for (int i = 0; i < method.getParameters().size(); i++) {
            putArgumentsIntoOperandStack(
                    method.getParameters().get(i).getType(),
                    processStatement.getArguments().get(i),
                    methodVisitor
            );
        }
        methodVisitor.visitMethodInsn(INVOKESTATIC,
                method.getOwner().getClassName(),
                processStatement.getName(),
                method.getDescriptor(),
                false);
    }

    private boolean isApplicable(ResolvedMethod method, List<Expression> arguments) {
        for (int i = 0; i < method.getParameters().size(); i++)
            if (!isApplicable(method.getParameters().get(i).getType(), arguments.get(i)))
                return false;
        return true;
    }

    private boolean isApplicable(ResolvedType type, Expression argument) {
        ResolvedType expressionType = expressionResolver.resolveExpression(argument);
        if (type.isExternal()) {
            return type.getExternalClass()
                    .isAssignableFrom(type.getExternalClass());
        } else
            return expressionType.getClassName().equals(type.getClassName()) &&
                    !expressionType.isExternal();
    }

    private void putArgumentsIntoOperandStack(
            ResolvedType type,
            Expression argument,
            MethodVisitor methodVisitor) {
        // todo: check argument
        expressionTranslator.translate(argument, methodVisitor);
    }
}
