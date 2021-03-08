import org.junit.Test;

import java.io.IOException;

public class TestGrammarParser {
    @Test
    public void parseSource() throws IOException {
        SyntaxParser parser = new SyntaxParser();
        parser.parse("HelloWorld.jer");
    }
}
