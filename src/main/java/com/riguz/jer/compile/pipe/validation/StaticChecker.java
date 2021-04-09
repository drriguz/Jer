package com.riguz.jer.compile.pipe.validation;

import com.riguz.jer.compile.exception.CompileException;
import com.riguz.jer.compile.pipe.pre.ClassDefinition;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaticChecker {
    public void check(List<ClassDefinition> definitions) throws CompileException {
        Set<String> names = new HashSet<>();
        for (ClassDefinition c : definitions) {
            if (!names.add(c.getFullName()))
                throw new CompileException("Type definition conflict:" + c.getFullName());
        }
    }
}
