package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.expression.*;
import com.riguz.jer.compile.def.statement.VariableDeclaration;
import com.riguz.jer.compile.def.statement.VariableDeclaration.VariableInitializer;
import com.riguz.jer.compile.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class TestParseExpressions {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));
    List<VariableDeclaration> constants;

    @Before
    public void setUp() throws ParseException {
        String source = "com/riguz/examples/constant/Expressions.jer";
        Script parsed = parser.parse(source);
        constants = parsed.getConstants();
    }

    @Test
    public void parseVariableReference() {
        VariableInitializer v = constants.get(1).getVariableInitializer();
        assertFalse(v.isArray());
        assertTrue(v.getValue() instanceof VariableReference);
        VariableReference f = v.getValue();
        assertEquals("foo", f.getVariableName());
    }

    @Test
    public void parseFunctionCall() {
        VariableInitializer v = constants.get(2).getVariableInitializer();
        assertFalse(v.isArray());
        assertTrue(v.getValue() instanceof FunctionCall);
        FunctionCall f = v.getValue();
        assertEquals("hello", ((Literal) f.getInstance()).getValue());
        assertEquals("append", f.getFunctionName());
        assertEquals(1, f.getArguments().size());
        assertEquals("world", ((Literal) f.getArguments().get(0)).getValue());

        VariableInitializer v1 = constants.get(6).getVariableInitializer();
        assertTrue(((FunctionCall) v1.getValue()).getArguments().isEmpty());
    }

    @Test
    public void parsePropertyValue() {
        VariableInitializer v = constants.get(3).getVariableInitializer();
        assertFalse(v.isArray());
        assertTrue(v.getValue() instanceof PropertyValue);
        PropertyValue f = v.getValue();
        assertEquals("person", ((VariableReference) f.getInstance()).getVariableName());
        assertEquals("name", f.getPropertyName());
    }

    @Test
    public void parseNestedExpression() {
        VariableInitializer v = constants.get(4).getVariableInitializer();
        assertFalse(v.isArray());
        assertTrue(v.getValue() instanceof FunctionCall);
        FunctionCall f = v.getValue();

        assertEquals("add", f.getFunctionName());
        assertEquals(1, f.getArguments().size());
        assertEquals("3", ((Literal) f.getArguments().get(0)).getValue());

        FunctionCall i = (FunctionCall) f.getInstance();
        assertEquals("add", i.getFunctionName());
        assertEquals(1, i.getArguments().size());
        assertEquals("2", ((Literal) i.getArguments().get(0)).getValue());
    }

    @Test
    public void parseObjectCreation() {
        VariableInitializer v = constants.get(5).getVariableInitializer();
        assertFalse(v.isArray());
        assertTrue(v.getValue() instanceof ObjectCreation);
        ObjectCreation f = v.getValue();
        assertEquals("Person", f.getType());
        assertEquals(2, f.getArguments().size());
    }
}