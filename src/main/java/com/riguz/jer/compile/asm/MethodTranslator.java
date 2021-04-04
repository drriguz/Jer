package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.CompileContext;
import com.riguz.jer.compile.def.Parameter;
import com.riguz.jer.compile.def.Process;
import com.riguz.jer.compile.util.SignatureBuilder;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;
import java.util.stream.Collectors;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class MethodTranslator {
    private final CompileContext context;

    public MethodTranslator(CompileContext context) {
        this.context = context;
    }

    public MethodNode translate(Process process) {
        MethodNode staticMethod = new MethodNode(ACC_PUBLIC + ACC_STATIC,
                process.getName(),
                createMethodDescriptor(process.getFormalParameters(), null),
                null,
                null
        );

        return staticMethod;
    }

    private String createMethodDescriptor(List<Parameter> parameters, String returnType) {
        List<String> params = parameters
                .stream()
                .map(Parameter::getType)
                .map(t -> TypeResolver.resolveTypeDescriptor(context, t))
                .collect(Collectors.toList());
        return SignatureBuilder.getMethodDescriptor(params, returnType);
    }

}
