package com.riguz.jer.compile.pipe.bytecode.asm;

import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.*;

public final class AsmUtil {
    private AsmUtil() {

    }

    public static ClassWriter createClassWriter(String name) {
        ClassWriter classWriter = new ClassWriter(COMPUTE_FRAMES);
        classWriter.visit(V1_8,
                ACC_PUBLIC + ACC_FINAL,
                name,
                null,
                "java/lang/Object",
                null);
        return classWriter;
    }
}
