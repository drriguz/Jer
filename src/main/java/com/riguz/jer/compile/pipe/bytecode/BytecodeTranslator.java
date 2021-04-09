package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.pipe.bytecode.asm.AbstractClassTranslator;
import com.riguz.jer.compile.pipe.bytecode.asm.DefaultClassTranslator;
import com.riguz.jer.compile.pipe.bytecode.asm.TypeClassTranslator;
import com.riguz.jer.compile.pipe.pre.AbstractClassDefinition;
import com.riguz.jer.compile.pipe.pre.ClassDefinition;
import com.riguz.jer.compile.pipe.pre.DefaultClassDefinition;
import com.riguz.jer.compile.pipe.pre.TypeClassDefinition;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BytecodeTranslator {
    public List<CompiledClass> translate(List<ClassDefinition> classes) {
        final Context context = new Context(classes);

        return classes.stream()
                .map(c -> translate(context, c))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<CompiledClass> translate(Context context, ClassDefinition definition) {
        if (definition instanceof DefaultClassDefinition)
            return new DefaultClassTranslator(context).translate((DefaultClassDefinition) definition);
        else if (definition instanceof AbstractClassDefinition)
            return new AbstractClassTranslator(context).translate((AbstractClassDefinition) definition);
        else if (definition instanceof TypeClassDefinition)
            return new TypeClassTranslator(context).translate((TypeClassDefinition) definition);
        else
            throw new IllegalArgumentException("Unexpected type");
    }
}
