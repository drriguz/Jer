package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.*;
import com.riguz.jer.compile.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestParseAbstract {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));
    List<Abstract> abstracts;

    @Before
    public void setUp() throws ParseException {
        String source = "com/riguz/examples/abstract/Abstracts.jer";
        Script parsed = parser.parse(source);
        abstracts = parsed.getAbstracts();
    }

    @Test
    public void parseAbstracts() {
        assertEquals(4, abstracts.size());
    }

    @Test
    public void parseEmptyAbstract() {
        Abstract a = abstracts.get(0);
        assertEquals("Empty", a.getName());
        assertTrue(a.getFunctionSignatures().isEmpty());
        assertTrue(a.getProperties().isEmpty());
    }

    @Test
    public void parsePurePropertiesAbstract() {
        Abstract a = abstracts.get(1);
        assertEquals("Properties", a.getName());
        assertTrue(a.getFunctionSignatures().isEmpty());
        assertEquals(1, a.getProperties().size());
        assertEquals(new Parameter("name", new VariableType("String")), a.getProperties().get(0));
    }

    @Test
    public void parsePureFunctionsAbstract() {
        Abstract a = abstracts.get(2);
        assertEquals("Functions", a.getName());
        assertEquals(1, a.getFunctionSignatures().size());
        assertEquals(new FunctionSignature("sayHello", Collections.emptyList(), new VariableType("String")),
                a.getFunctionSignatures().get(0));
        assertTrue(a.getProperties().isEmpty());
    }

    @Test
    public void parseComplexAbstract() {
        Abstract a = abstracts.get(3);
        assertEquals("Animal", a.getName());
        assertEquals(1, a.getProperties().size());
        assertEquals(3, a.getFunctionSignatures().size());
    }
}