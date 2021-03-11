package com.riguz.jer.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.antlr.AntlrParser;
import com.riguz.jer.compile.exception.CompileException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;


@RunWith(Parameterized.class)
public class TestGrammarParserWithBadSyntax {
    private final String source;

    public TestGrammarParserWithBadSyntax(String source) {
        this.source = source;
    }

    @Parameterized.Parameters
    public static Collection<String> cases() {
        return Arrays.asList(
                "com/riguz/examples/grammar/error/EmptySource.jer",
                "com/riguz/examples/grammar/error/ConstantWithoutValue.jer"
        );
    }

    @Test(expected = ParseCancellationException.class)
    public void parseSource() throws IOException {
        Parser parser = new AntlrParser(Paths.get("src/test/resources"));
        parser.parse(source);
    }
}
