package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.CompileContext;
import com.riguz.jer.compile.exception.CompileException;
import org.junit.Test;

import static com.riguz.jer.compile.asm.TypeResolver.resolveTypeDescriptor;
import static org.junit.Assert.*;

public class TypeResolverTest {
    CompileContext context = new CompileContext(sources, null);

    @Test
    public void resolveJavaLangClasses() {
        assertEquals("Ljava/lang/String;", resolveTypeDescriptor(context, "String"));
    }

    @Test
    public void resolveArrayType() {
        assertEquals("[Ljava/lang/String;", resolveTypeDescriptor(context, "[String"));
        assertEquals("[[Ljava/lang/String;", resolveTypeDescriptor(context, "[[String"));
    }

    @Test(expected = CompileException.class)
    public void throwIfNotResolved() {
        resolveTypeDescriptor(context, "ABC");
    }
}