package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.pipe.bytecode.CompiledClass;

import java.util.List;

public class CompiledClassLoader extends ClassLoader {
    private final List<CompiledClass> compiledClasses;

    public CompiledClassLoader(List<CompiledClass> compiledClasses) {
        this.compiledClasses = compiledClasses;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String innerName = name.replaceAll("\\.", "/");
        Class<?> resolved = compiledClasses.stream()
                .filter(c -> c.getClassFullName().equals(innerName))
                .findAny()
                .map(target -> defineClass(name, target.getBytes(), 0, target.getBytes().length))
                .orElse(null);

        return resolved != null ? resolved : super.findClass(name);
    }
}
