package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;
import org.junit.Test;

import static org.junit.Assert.*;

public class AntlrParserTest {
    final Parser parser = new AntlrParser();

    @Test
    public void detectSourceFilePackage() {
        String source = "examples/com/riguz/HelloWorld.jer";
        Script parsed = parser.parse(source);

        assertNotNull(parsed);
        assertEquals("examples/com/riguz", parsed.getPackageName());
        assertEquals("HelloWorld.jer", parsed.getFileName());
    }

    @Test(expected = CompileException.class)
    public void throwExceptionIfNotExists() {
        String source = "examples/com/riguz/NotExists.jer";
        Script parsed = parser.parse(source);
    }
}