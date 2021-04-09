package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.pipe.bytecode.ClassTranslator;
import com.riguz.jer.compile.pipe.bytecode.CompiledClass;
import com.riguz.jer.compile.pipe.bytecode.Context;
import com.riguz.jer.compile.pipe.pre.DefaultClassDefinition;
import org.objectweb.asm.tree.ClassNode;

import java.util.Collections;
import java.util.List;

import static com.riguz.jer.compile.pipe.bytecode.asm.AsmUtil.createClass;
import static com.riguz.jer.compile.pipe.bytecode.asm.AsmUtil.createCompileClass;

public class DefaultClassTranslator extends ClassTranslator<DefaultClassDefinition> {
    public DefaultClassTranslator(Context context) {
        super(context);
    }

    @Override
    public List<CompiledClass> translate(DefaultClassDefinition source) {
        ClassNode defaultClass = createClass(source.getFullName());
        return Collections.singletonList(createCompileClass(
                source.getFullName() + ".class",
                defaultClass));
    }
}
