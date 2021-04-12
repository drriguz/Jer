package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.VariableType;
import com.riguz.jer.compile.def.expression.Literal;
import com.riguz.jer.compile.def.statement.VariableDeclaration;
import com.riguz.jer.compile.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class TestParsePrimitiveConstantDeclaration {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));
    List<VariableDeclaration> constants;

    @Before
    public void setUp() throws ParseException {
        String source = "com/riguz/examples/constant/PrimitiveTypes.jer";
        Script parsed = parser.parse(source);
        constants = parsed.getConstants();
    }

    @Test
    public void parseConstants() {
        assertEquals(9, constants.size());
    }


    @Test
    public void parsePrimitiveTypes() {
        verify(constants.get(0), "b", "Byte", "19");
        verify(constants.get(1), "st", "Short", "111");
        verify(constants.get(2), "kb", "Integer", "1024");
        verify(constants.get(3), "id", "Long", "12345678");
        verify(constants.get(4), "db", "Double", "581216732.323433");
        verify(constants.get(5), "pi", "Float", "3.1415926");
        verify(constants.get(6), "success", "Boolean", "true");
        verify(constants.get(7), "ch", "Char", "A");
        verify(constants.get(8), "msg", "String", "hello\\n world!");
    }

    private void verify(VariableDeclaration parsed, String name, String type, String expected) {
        assertEquals(name, parsed.getVariableName());
        assertEquals(new VariableType(type), parsed.getType());

        VariableDeclaration.VariableInitializer variableInitializer = parsed.getVariableInitializer();
        assertFalse(variableInitializer.isArray());
        assertNotNull("variable " + name + " should have initialize value", variableInitializer.getValue());

        Literal literal = variableInitializer.getValue();
        assertEquals(expected, literal.getValue());
    }
}