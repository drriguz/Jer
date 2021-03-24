package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.expression.Literal;
import com.riguz.jer.compile.def.statement.VariableDeclaration;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestParseExpressions {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));
    List<VariableDeclaration> constants;

    @Before
    public void setUp() {
        String source = "com/riguz/examples/constant/Expressions.jer";
        Script parsed = parser.parse(source);
        constants = parsed.getConstants();
    }

    @Test
    public void parseExpressions() {

    }
}