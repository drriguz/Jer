package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.pipe.bytecode.CompiledClass;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.*;

public final class AsmUtil {
    private AsmUtil() {

    }

    public static ClassNode createClass(String name) {
        ClassNode classNode = new ClassNode();
        classNode.version = V1_8;
        classNode.access = ACC_PUBLIC + ACC_FINAL;
        classNode.name = name;
        classNode.superName = "java/lang/Object";
        return classNode;
    }

    public static CompiledClass createCompileClass(
            String fileName,
            ClassNode classNode) {
        ClassWriter writer = new ClassWriter(COMPUTE_FRAMES);
        classNode.accept(writer);
        byte[] bytes = writer.toByteArray();

        return new CompiledClass(
                fileName,
                bytes);
    }
}
