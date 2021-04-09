package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.CompileContext;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;
import com.riguz.jer.compile.pipe.bytecode.CompiledClass;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;

import static com.riguz.jer.compile.pipe.bytecode.asm.AsmUtil.createClass;
import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;

public class AsmByteCodeTranslator implements ByteCodeTranslator {

    @Override
    public List<CompiledClass> translate(CompileContext context, Script script) throws CompileException {
        List<CompiledClass> complied = new ArrayList<>();
        Scope scope = new Scope(context, script);
        registerImportedTypes(script, context);

        if (!script.getConstants().isEmpty() ||
                !script.getProcesses().isEmpty()) {
            ClassNode defaultClass = generateDefaultClass(scope, script);
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

    private ClassNode generateDefaultClass(Scope scope, Script script) {
        ClassNode defaultClass = createClass("");
        MethodTranslator methodTranslator = new MethodTranslator(scope);
        script.getProcesses().forEach(p -> {
            MethodNode methodNode = methodTranslator.translate(p);
            defaultClass.methods.add(methodNode);
        });
        return defaultClass;
    }

    private String getCompiledFileName(String packageName, String sourceFileName) {
        return packageName + "/" +
                sourceFileName.replace(".jer", ".class");
    }

}
