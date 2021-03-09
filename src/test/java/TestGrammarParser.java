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
                "HelloWorld.jer",
                "EmptyFunction.jer",
                "LocalVariables.jer",
                "Control.jer",
                "Functions.jer",
                "Abstract.jer",
                "CircleType.jer",
                "AllInOne.jer",
                "Expressions.jer",
                "Constructors.jer",
                "Array.jer"
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
