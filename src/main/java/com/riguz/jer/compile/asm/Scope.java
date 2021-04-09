package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.CompileContext;
import com.riguz.jer.compile.def.Script;

public class Scope {
    private final CompileContext context;
    private final Script script;

    public Scope(CompileContext context, Script script) {
        this.context = context;
        this.script = script;
    }

    public CompileContext getContext() {
        return context;
    }

    public Script getScript() {
        return script;
    }
}
