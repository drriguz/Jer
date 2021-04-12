package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.def.VariableType;
import com.riguz.jer.compile.exception.CompileException;
import com.riguz.jer.compile.pipe.pre.AbstractClassDefinition;
import com.riguz.jer.compile.pipe.pre.ConstClassDefinition;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TypeResolverTest {
    private final ConstClassDefinition constClassDefinition =
            new ConstClassDefinition(
                    "HelloWorld",
                    "com/riguz",
                    Map.of("A", "com/riguz/another/A",
                            "B", "com/riguz/another/B",
                            "C", "com/riguz/another/C",
                            "Map", "java/util/Map"),
                    Collections.emptyList(),
                    Collections.emptyList()
            );
    private final AbstractClassDefinition a =
            new AbstractClassDefinition(
                    "A",
                    "com/riguz",
                    Collections.emptyMap(),
                    Collections.emptyList(),
                    Collections.emptyList()
            );
    private final AbstractClassDefinition b =
            new AbstractClassDefinition(
                    "B",
                    "com/riguz/another",
                    Collections.emptyMap(),
                    Collections.emptyList(),
                    Collections.emptyList()
            );

    Context context = new Context(Arrays.asList(constClassDefinition, a, b));
    TypeResolver resolver = new TypeResolver(context);

    @Test
    public void resolveTypeFromSamePackage() {
        ResolvedType self = resolver.resolveType(constClassDefinition, new VariableType("HelloWorld"));
        assertEquals("com/riguz/HelloWorld", self.getClassName());

        ResolvedType a = resolver.resolveType(constClassDefinition, new VariableType("A"));
        assertEquals("com/riguz/A", a.getClassName());
    }

    @Test
    public void resolveImportedInternalTypes() {
        ResolvedType b = resolver.resolveType(constClassDefinition, new VariableType("B"));
        assertEquals("com/riguz/another/B", b.getClassName());
    }

    @Test
    public void resolveImportedExternalTypes() {
        ResolvedType b = resolver.resolveType(constClassDefinition, new VariableType("String"));
        assertEquals("java/lang/String", b.getClassName());

        ResolvedType b1 = resolver.resolveType(constClassDefinition, new VariableType("Map"));
        assertEquals("java/util/Map", b1.getClassName());
    }

    @Test(expected = CompileException.class)
    public void throwIfNotResolved() {
        ResolvedType self = resolver.resolveType(constClassDefinition, new VariableType("SB"));
        assertEquals("com/riguz/HelloWorld", self.getClassName());
    }

    @Test(expected = CompileException.class)
    public void throwIfResolvedButSourceOrClassNotLoaded() {
        ResolvedType self = resolver.resolveType(constClassDefinition, new VariableType("C"));
    }
}