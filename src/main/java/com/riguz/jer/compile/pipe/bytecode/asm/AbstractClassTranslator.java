package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.pipe.bytecode.ClassTranslator;
import com.riguz.jer.compile.pipe.bytecode.CompiledClass;
import com.riguz.jer.compile.pipe.bytecode.Context;
import com.riguz.jer.compile.pipe.pre.AbstractClassDefinition;

import java.util.Collections;
import java.util.List;

public class AbstractClassTranslator extends ClassTranslator<AbstractClassDefinition> {
    public AbstractClassTranslator(Context context) {
        super(context);
    }

    @Override
    public List<CompiledClass> translate(AbstractClassDefinition source) {
        return Collections.emptyList();
    }
}
