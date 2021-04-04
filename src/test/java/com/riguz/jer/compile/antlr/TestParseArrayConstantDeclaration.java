package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.expression.Literal;
import com.riguz.jer.compile.def.statement.VariableDeclaration;
import com.riguz.jer.compile.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestParseArrayConstantDeclaration {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));
    List<VariableDeclaration> constants;

    @Before
    public void setUp() throws ParseException {
        String source = "com/riguz/examples/constant/ArrayTypes.jer";
        Script parsed = parser.parse(source);
        constants = parsed.getConstants();
    }

    @Test
    public void parseArrayTypes() {
        verify(constants.get(0), "arri", "[Integer", Arrays.asList("1", "2", "3", "4", "5"));
        verify(constants.get(1), "arrstr", "[String", Arrays.asList("hello", "world"));
        verify(constants.get(2), "arr2d", "[[Integer",
                Arrays.asList(Arrays.asList("1", "2", "3"),
                        Arrays.asList("4","5", "6"),
                        Arrays.asList("7", "8", "9")));
    }

    private <T> void verify(VariableDeclaration parsed,
                            String name,
                            String type,
                            List<T> expected) {
        assertEquals(name, parsed.getVariableName());
        assertEquals(type, parsed.getType());

        VariableDeclaration.VariableInitializer variableInitializer = parsed.getVariableInitializer();
        assertTrue(variableInitializer.isArray());
        assertFalse("variable " + name + " should have non-empty array initialize value",
                variableInitializer.getArrayInitializer().isEmpty());

        List<VariableDeclaration.VariableInitializer> v = variableInitializer.getArrayInitializer();
        assertEquals(expected.size(), v.size());

        int arrayDimensions = type.lastIndexOf('[') + 1;
        for (int i = 0; i < expected.size(); i++) {
            if (arrayDimensions == 1) {
                assertFalse(v.get(i).isArray());
                Literal literal = v.get(i).getValue();
                assertEquals(expected.get(i), literal.getValue());
            } else if (arrayDimensions == 2) {
                assertTrue(v.get(i).isArray());
                List<List<String>> expectedValues = (List<List<String>>) expected;
                for (int j = 0; j < expectedValues.get(i).size(); j++) {
                    assertFalse(v.get(i).getArrayInitializer().get(j).isArray());
                    Literal literal = v.get(i).getArrayInitializer().get(j).getValue();
                    assertEquals(expectedValues.get(i).get(j), literal.getValue());
                }
            } else
                throw new IllegalArgumentException("Unexpected test branch");
        }
    }
}