import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TestGrammarParser {
    private final String source;

    public TestGrammarParser(String source) {
        this.source = source;
    }

    @Parameterized.Parameters
    public static Collection<String> cases() {
        return Arrays.asList(
                "HelloWorld.jer",
                "EmptyFunction.jer",
                "LocalVariables.jer"
        );
    }

    @Test
    public void parseSource() throws IOException {
        SyntaxParser parser = new SyntaxParser();
        parser.parse(source);
    }
}
