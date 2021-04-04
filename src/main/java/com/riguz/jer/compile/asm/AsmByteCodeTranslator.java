package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.ByteCodeTranslator;
import com.riguz.jer.compile.CompiledClass;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;

import java.util.ArrayList;
import java.util.List;

public class AsmByteCodeTranslator implements ByteCodeTranslator {
    @Override
    public List<CompiledClass> translate(Script script) throws CompileException {
        List<CompiledClass> complied = new ArrayList<>();

        if (!script.getConstants().isEmpty() ||
                !script.getProcesses().isEmpty()) {
            complied.add(new CompiledClass(
                    getCompiledFileName(script.getPackageName(), script.getFileName()), null));
        }
        return complied;
    }

    private String getCompiledFileName(String packageName, String sourceFileName) {
        return packageName + "/" +
                sourceFileName.replace(".jer", ".class");
    }

}
