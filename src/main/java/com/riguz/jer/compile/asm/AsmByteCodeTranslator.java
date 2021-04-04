package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.ByteCodeTranslator;
import com.riguz.jer.compile.CompileContext;
import com.riguz.jer.compile.CompiledClass;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.*;

public class AsmByteCodeTranslator implements ByteCodeTranslator {
    private final CompileContext context;

    public AsmByteCodeTranslator(CompileContext context) {
        this.context = context;
    }

    @Override
    public List<CompiledClass> translate(Script script) throws CompileException {
        List<CompiledClass> complied = new ArrayList<>();

        if (!script.getConstants().isEmpty() ||
                !script.getProcesses().isEmpty()) {
            ClassNode defaultClass = generateDefaultClass(script);
            complied.add(
                    createCompileClass(
                            getCompiledFileName(script.getPackageName(), script.getFileName()),
                            defaultClass));
        }
        return complied;
    }

    private CompiledClass createCompileClass(
            String fileName,
            ClassNode classNode) {
        ClassWriter writer = new ClassWriter(COMPUTE_FRAMES);
        classNode.accept(writer);
        byte[] bytes = writer.toByteArray();

        return new CompiledClass(
                fileName,
                bytes);
    }

    private ClassNode generateDefaultClass(Script script) {
        ClassNode defaultClass = createClass(script.getFileName()
                .replace(".jer", ""));

        return defaultClass;
    }

    private ClassNode createClass(String name) {
        ClassNode classNode = new ClassNode();
        classNode.version = V1_8;
        classNode.access = ACC_PUBLIC + ACC_FINAL;
        classNode.name = name;
        classNode.superName = "java/lang/Object";

        return classNode;
    }


    private String getCompiledFileName(String packageName, String sourceFileName) {
        return packageName + "/" +
                sourceFileName.replace(".jer", ".class");
    }

}
