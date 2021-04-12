package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.*;
import com.riguz.jer.compile.def.expression.Literal;
import com.riguz.jer.compile.def.statement.ReturnStatement;
import com.riguz.jer.compile.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestParseType {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));
    List<Type> types;

    @Before
    public void setUp() throws ParseException {
        String source = "com/riguz/examples/type/Simple.jer";
        Script parsed = parser.parse(source);
        types = parsed.getTypes();
    }

    @Test
    public void parseAbstracts() {
        assertEquals(4, types.size());
    }

    @Test
    public void parseEmptyAbstract() {
        Type a = types.get(0);
        assertEquals("Empty", a.getName());
        assertTrue(a.getConstructors().isEmpty());
        assertTrue(a.getFunctions().isEmpty());
        assertTrue(a.getProperties().isEmpty());
    }

    @Test
    public void parsePurePropertiesAbstract() {
        Type a = types.get(1);
        assertEquals("PureProperties", a.getName());
        assertTrue(a.getFunctions().isEmpty());
        assertEquals(1, a.getProperties().size());
        assertEquals(new Parameter("age", new VariableType("Integer")), a.getProperties().get(0));
    }

    @Test
    public void parsePureFunctionsAbstract() {
        Type a = types.get(2);
        assertEquals("PureFunctions", a.getName());
        assertEquals(1, a.getFunctions().size());
        assertEquals(new Function("sayHello",
                        Collections.emptyList(),
                        new VariableType("String"),
                        new Block(Arrays.asList(new ReturnStatement(new Literal("Hello world!"))))),
                a.getFunctions().get(0));
        assertTrue(a.getProperties().isEmpty());
    }

    @Test
    public void parseComplexAbstract() {
        Type a = types.get(3);
        assertEquals("Complex", a.getName());
        assertEquals(3, a.getAbstractions().size());
        assertEquals("A", a.getAbstractions().get(0));
        assertEquals(1, a.getProperties().size());
        assertEquals(1, a.getFunctions().size());
    }
}