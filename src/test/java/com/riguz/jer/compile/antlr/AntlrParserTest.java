package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

public class AntlrParserTest {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));

    @Test
    public void detectSourceFilePackage() {
        String source = "com/riguz/examples/HelloWorld.jer";
        Script parsed = parser.parse(source);

        assertNotNull(parsed);
        assertNotNull(parsed);
        assertEquals("com/riguz/examples", parsed.getPackageName());
        assertEquals("HelloWorld.jer", parsed.getFileName());
    }

    @Test(expected = CompileException.class)
    public void throwExceptionIfNotExists() {
        String source = "examples/com/riguz/NotExists.jer";
        Script parsed = parser.parse(source);
    }

    @Test
    public void parseImportedTypes() {
        String source = "com/riguz/examples/HelloWorld.jer";
        Script parsed = parser.parse(source);

        assertEquals(1, parsed.getImportedTypes().size());
        assertEquals("jer/lang/System", parsed.getImportedTypes().get(0));
    }
}