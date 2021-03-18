package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.expression.Literal;
import com.riguz.jer.compile.def.statement.VariableDeclaration;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestParseConstantDeclaration {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));

    @Test
    public void parseImportedTypes() {
        String source = "com/riguz/examples/constant/PrimitiveTypes.jer";
        Script parsed = parser.parse(source);
        List<VariableDeclaration> constants = parsed.getConstants();
        assertEquals(5, constants.size());

        assertEquals("pi", constants.get(0).getVariableName());
        assertEquals("Float", constants.get(0).getType());

        VariableDeclaration.VariableInitializer variableInitializer = constants.get(0).getVariableInitializer();
        assertFalse(variableInitializer.isArray());
        assertEquals("3.1415926f", ((Literal)variableInitializer.getValue()).getValue());
    }
}