package com.riguz.jer.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.antlr.AntlrParser;
import com.riguz.jer.compile.exception.ParseException;
import org.junit.Test;

import java.nio.file.Paths;


public class TestGrammarParser {

    @Test
    public void parseSource() throws ParseException {
        Parser parser = new AntlrParser(Paths.get("src/test/resources"));
        parser.parse("com/riguz/examples/grammar/AllInOne.jer");
    }
}
