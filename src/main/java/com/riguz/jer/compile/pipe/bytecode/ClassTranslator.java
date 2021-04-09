package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.pipe.pre.ClassDefinition;

import java.util.List;

public abstract class ClassTranslator<T extends ClassDefinition> {
    protected final Context context;

    protected ClassTranslator(Context context) {
        this.context = context;
    }

    public abstract List<CompiledClass> translate(T source);
}
