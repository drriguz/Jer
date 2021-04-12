package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.def.Parameter;
import com.riguz.jer.compile.def.Process;
import com.riguz.jer.compile.def.Statement;
import com.riguz.jer.compile.pipe.bytecode.*;
import com.riguz.jer.compile.pipe.pre.ConstClassDefinition;
import com.riguz.jer.compile.util.SignatureBuilder;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.riguz.jer.compile.pipe.bytecode.asm.AsmUtil.createClass;
import static com.riguz.jer.compile.pipe.bytecode.asm.AsmUtil.createCompileClass;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class DefaultClassTranslator extends ClassTranslator<ConstClassDefinition> {
    private final TypeResolver typeResolver;

    public DefaultClassTranslator(Context context) {
        super(context);
        this.typeResolver = new TypeResolver(context);
    }

    @Override
    public List<CompiledClass> translate(ConstClassDefinition source) {
        ClassNode defaultClass = createClass(source.getFullName());
        List<MethodNode> processes = source.getProcesses()
                .stream()
                .map(p -> translateProcess(source, p))
                .collect(Collectors.toList());
        defaultClass.methods.addAll(processes);
        return Collections.singletonList(createCompileClass(
                source.getFullName() + ".class",
                defaultClass));
    }

    private MethodNode translateProcess(ConstClassDefinition source, Process process) {
        MethodNode staticMethod = new MethodNode(
                ACC_PUBLIC + ACC_STATIC,
                process.getName(),
                createMethodDescriptor(source, process.getFormalParameters()),
                null,
                null
        );

        InsnList instructions = translateStatements(process.getBlock().getStatements());
        staticMethod.instructions.add(instructions);

        return staticMethod;
    }

    private InsnList translateStatements(List<Statement> statements) {
        InsnList instructions = new InsnList();

        return instructions;
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
