package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.ParseException;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TestParsePackageAndImportedTypes {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));

    @Test
    public void detectSourceFilePackage() throws ParseException {
        String source = "com/riguz/examples/HelloWorld.jer";
        Script parsed = parser.parse(source);

        assertNotNull(parsed);
        assertNotNull(parsed);
        assertEquals("com/riguz/examples", parsed.getPackageName());
        assertEquals("HelloWorld.jer", parsed.getFileName());
    }

    @Test(expected = ParseException.class)
    public void throwExceptionIfNotExists() throws ParseException {
        String source = "examples/com/riguz/NotExists.jer";
        Script parsed = parser.parse(source);
    }

    @Test
    public void parseImportedTypes() throws ParseException {
        String source = "com/riguz/examples/HelloWorld.jer";
        Script parsed = parser.parse(source);

        assertEquals(1, parsed.getImportedTypes().size());
        assertEquals("jer/lang/System", parsed.getImportedTypes().get(0));
    }
}