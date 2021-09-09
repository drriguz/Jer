package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.def.Parameter;
import com.riguz.jer.compile.def.Process;
import com.riguz.jer.compile.def.Statement;
import com.riguz.jer.compile.pipe.bytecode.*;
import com.riguz.jer.compile.pipe.pre.ConstClassDefinition;
import com.riguz.jer.compile.util.SignatureBuilder;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.InsnList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.riguz.jer.compile.pipe.bytecode.asm.AsmUtil.createClassWriter;
import static org.objectweb.asm.Opcodes.*;

public class ConstClassTranslator extends ClassTranslator<ConstClassDefinition> {
    private final TypeResolver typeResolver;

    public ConstClassTranslator(Context context) {
        super(context);
        this.typeResolver = new TypeResolver(context);
    }

    @Override
    public List<CompiledClass> translate(ConstClassDefinition source) {
        ClassWriter classWriter = createClassWriter(source.getFullName());
        source.getProcesses()
                .forEach(p -> translateProcess(classWriter, source, p));
        classWriter.visitEnd();
        CompiledClass defaultClass = new CompiledClass(
                source.getFullName(),
                classWriter.toByteArray());
        return Collections.singletonList(defaultClass);
    }

    private void translateProcess(ClassWriter classWriter,
                                  ConstClassDefinition source,
                                  Process process) {
        MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_STATIC,
                process.getName(),
                createMethodDescriptor(source, process.getFormalParameters()),
                null,
                null
        );
        methodVisitor.visitCode();
        generateProcessCode(methodVisitor, source, process.getBlock().getStatements());
        methodVisitor.visitEnd();
    }

    private void generateProcessCode(MethodVisitor methodVisitor,
                                     ConstClassDefinition source,
                                     List<Statement> statements) {
        StatementTranslator statementTranslator = new StatementTranslator(context, source);
        statements
                .forEach(s -> statementTranslator.translate(s, methodVisitor));
    }

    private String createMethodDescriptor(ConstClassDefinition source,
                                          List<Parameter> parameters) {
        List<String> params = parameters
                .stream()
                .map(Parameter::getType)
                .map(t -> typeResolver.resolveType(source, t))
                .map(ResolvedType::getDescriptor)
                .collect(Collectors.toList());
        return SignatureBuilder.getMethodDescriptor(params, "V");
    }
}
