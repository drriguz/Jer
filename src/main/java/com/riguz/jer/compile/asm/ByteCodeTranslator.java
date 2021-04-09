package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.CompileContext;
import com.riguz.jer.compile.pipe.bytecode.CompiledClass;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;

import java.util.List;

public interface ByteCodeTranslator {
    List<CompiledClass> translate(CompileContext context, Script script) throws CompileException;
}
