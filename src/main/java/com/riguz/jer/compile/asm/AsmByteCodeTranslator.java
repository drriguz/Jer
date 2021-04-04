package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.ByteCodeTranslator;
import com.riguz.jer.compile.CompileContext;
import com.riguz.jer.compile.CompiledClass;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.Process;
import com.riguz.jer.compile.exception.CompileException;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.*;

public class AsmByteCodeTranslator implements ByteCodeTranslator {

    @Override
    public List<CompiledClass> translate(Script script) throws CompileException {
        List<CompiledClass> complied = new ArrayList<>();
        CompileContext context = new CompileContext();
        registerImportedTypes(script, context);

        if (!script.getConstants().isEmpty() ||
                !script.getProcesses().isEmpty()) {
            ClassNode defaultClass = generateDefaultClass(context, script);
            complied.add(
                    createCompileClass(
                            getCompiledFileName(script.getPackageName(), script.getFileName()),
                            defaultClass));
        }
        return complied;
    }

    private void registerImportedTypes(Script script, CompileContext context) {
        script.getImportedTypes()
                .forEach(i -> {
                    String type = i.substring(i.lastIndexOf("/") + 1);
                    context.addFullQualifiedType(type, i);
                });
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

    private ClassNode generateDefaultClass(CompileContext context, Script script) {
        ClassNode defaultClass = createClass(
                script.getPackageName() + "/" + script.getFileName()
                        .replace(".jer", ""));
        MethodTranslator methodTranslator = new MethodTranslator(context);
        script.getProcesses().forEach(p -> {
            MethodNode methodNode = methodTranslator.translate(p);
            defaultClass.methods.add(methodNode);
        });
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
