package com.riguz.jer.compile;

import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;

import java.util.List;

public interface ByteCodeTranslator {
    List<CompiledClass> translate(Script script) throws CompileException;
}
