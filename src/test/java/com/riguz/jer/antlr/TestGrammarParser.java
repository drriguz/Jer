package com.riguz.jer.antlr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.fail;


@RunWith(Parameterized.class)
public class TestGrammarParser {
    private final String source;

    public TestGrammarParser(String source) {
        this.source = source;
    }

    @Parameterized.Parameters
    public static Collection<String> cases() {
        return Arrays.asList(
                "com/riguz/examples/HelloWorld.jer",
                "com/riguz/examples/EmptyFunction.jer",
                "com/riguz/examples/LocalVariables.jer",
                "com/riguz/examples/Control.jer",
                "com/riguz/examples/Functions.jer",
                "com/riguz/examples/Abstract.jer",
                "com/riguz/examples/CircleType.jer",
                "com/riguz/examples/AllInOne.jer",
                "com/riguz/examples/Expressions.jer",
                "com/riguz/examples/Constructors.jer",
                "com/riguz/examples/Array.jer"
        );
    }

    @Test
    public void parseSource() throws IOException {
        SyntaxParser parser = new SyntaxParser();
        try {
            parser.parse(source);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Failed to parse:" + source + " " + ex.getMessage());
        }
    }
}
