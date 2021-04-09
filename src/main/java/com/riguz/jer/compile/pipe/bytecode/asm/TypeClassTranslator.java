package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.pipe.bytecode.ClassTranslator;
import com.riguz.jer.compile.pipe.bytecode.CompiledClass;
import com.riguz.jer.compile.pipe.bytecode.Context;
import com.riguz.jer.compile.pipe.pre.TypeClassDefinition;

import java.util.Collections;
import java.util.List;

public class TypeClassTranslator extends ClassTranslator<TypeClassDefinition> {
    public TypeClassTranslator(Context context) {
        super(context);
    }

    @Override
    public List<CompiledClass> translate(TypeClassDefinition source) {
        return Collections.emptyList();
    }
}
