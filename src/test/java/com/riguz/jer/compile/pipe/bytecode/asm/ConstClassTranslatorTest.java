package com.riguz.jer.compile.pipe.bytecode.asm;

import com.riguz.jer.compile.def.Block;
import com.riguz.jer.compile.def.Parameter;
import com.riguz.jer.compile.def.Process;
import com.riguz.jer.compile.def.VariableType;
import com.riguz.jer.compile.pipe.bytecode.CompiledClass;
import com.riguz.jer.compile.pipe.bytecode.Context;
import com.riguz.jer.compile.pipe.pre.ConstClassDefinition;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ConstClassTranslatorTest {
    @Test
    public void generateClass() {
        ConstClassDefinition definition = new ConstClassDefinition(
                "HelloWorld",
                "com/riguz",
                Collections.emptyMap(),
                Collections.emptyList(),
                Collections.emptyList());
        ConstClassTranslator translator = new ConstClassTranslator(
                new Context(Arrays.asList(definition)));

        List<CompiledClass> compiled = translator.translate(definition);
        assertEquals(1, compiled.size());
        assertEquals("com/riguz/HelloWorld", compiled.get(0).getClassFullName());
        assertEquals("com/riguz/HelloWorld.java", compiled.get(0).getFileName());
        assertNotNull(compiled.get(0).getBytes());
    }

    @Test
    public void translateProcess() throws ClassNotFoundException, IOException {
        ConstClassDefinition definition = new ConstClassDefinition(
                "HelloWorld",
                "com/riguz",
                Collections.emptyMap(),
                Collections.emptyList(),
                Arrays.asList(
                        new Process("sayHello",
                                Arrays.asList(
                                        new Parameter("message", new VariableType("String"))
                                ),
                                new Block(Collections.emptyList()))));
        ConstClassTranslator translator = new ConstClassTranslator(
                new Context(Arrays.asList(definition)));

        List<CompiledClass> compiled = translator.translate(definition);
        assertEquals(1, compiled.size());
        assertEquals("com/riguz/HelloWorld", compiled.get(0).getClassFullName());
        assertEquals("com/riguz/HelloWorld.java", compiled.get(0).getFileName());
        assertNotNull(compiled.get(0).getBytes());

        JavaClass j = parse(compiled.get(0));
        assertEquals(1, j.getMethods().length);
    }

    private JavaClass parse(CompiledClass compiledClass) throws IOException {
        ClassParser classParser = new ClassParser(new ByteArrayInputStream(compiledClass.getBytes()),
                compiledClass.getFileName());
        return classParser.parse();
    }
}