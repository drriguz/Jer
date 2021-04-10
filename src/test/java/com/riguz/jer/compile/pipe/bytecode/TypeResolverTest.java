package com.riguz.jer.compile.pipe.bytecode;

import com.riguz.jer.compile.exception.CompileException;
import com.riguz.jer.compile.pipe.pre.AbstractClassDefinition;
import com.riguz.jer.compile.pipe.pre.DefaultClassDefinition;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TypeResolverTest {
    private final DefaultClassDefinition defaultClassDefinition =
            new DefaultClassDefinition(
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

    Context context = new Context(Arrays.asList(defaultClassDefinition, a, b));
    TypeResolver resolver = new TypeResolver(context);

    @Test
    public void resolveTypeFromSamePackage() {
        JavaType self = resolver.resolveTypeDescriptor(defaultClassDefinition, "HelloWorld");
        assertEquals("com/riguz/HelloWorld", self.getClassName());

        JavaType a = resolver.resolveTypeDescriptor(defaultClassDefinition, "A");
        assertEquals("com/riguz/A", a.getClassName());
    }

    @Test
    public void resolveImportedInternalTypes() {
        JavaType b = resolver.resolveTypeDescriptor(defaultClassDefinition, "B");
        assertEquals("com/riguz/another/B", b.getClassName());
    }

    @Test
    public void resolveImportedExternalTypes() {
        JavaType b = resolver.resolveTypeDescriptor(defaultClassDefinition, "String");
        assertEquals("java/lang/String", b.getClassName());

        JavaType b1 = resolver.resolveTypeDescriptor(defaultClassDefinition, "Map");
        assertEquals("java/util/Map", b1.getClassName());
    }

    @Test(expected = CompileException.class)
    public void throwIfNotResolved() {
        JavaType self = resolver.resolveTypeDescriptor(defaultClassDefinition, "SB");
        assertEquals("com/riguz/HelloWorld", self.getClassName());
    }

    @Test(expected = CompileException.class)
    public void throwIfResolvedButSourceOrClassNotLoaded() {
        JavaType self = resolver.resolveTypeDescriptor(defaultClassDefinition, "C");
    }
}