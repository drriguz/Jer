package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.def.Statement;
import com.riguz.jer.compile.def.statement.ProcessStatement;
import com.riguz.jer.compile.pipe.bytecode.Context;
import com.riguz.jer.compile.pipe.bytecode.TypeResolver;
import com.riguz.jer.compile.pipe.pre.ClassDefinition;
import com.riguz.jer.compile.pipe.pre.ConstClassDefinition;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;

import static org.objectweb.asm.Opcodes.*;

public class StatementTranslator {
    private final Context context;
    private final ClassDefinition providerClass;
    private final TypeResolver typeResolver;

    public StatementTranslator(Context context, ClassDefinition providerClass) {
        this.context = context;
        this.providerClass = providerClass;
        this.typeResolver = new TypeResolver(context);
    }

    public InsnList translate(Statement statement) {
        InsnList instructions = new InsnList();
        if (statement instanceof ProcessStatement) {
            ProcessStatement p = (ProcessStatement) statement;
            instructions.add(new MethodInsnNode(INVOKESTATIC,
                    "java/io/PrintStream",
                    "println",
                    "(Ljava/lang/String;)V"));
        }
        return instructions;
    }

    private String findProcessMethod(String methodName) {
        // find process from current script
        if (providerClass instanceof ConstClassDefinition) {

        }

        // todo:
        return null;
    }
}
