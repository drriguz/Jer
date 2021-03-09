import com.riguz.jer.antlr.generated.JerLexer;
import com.riguz.jer.antlr.generated.JerParser;
import org.antlr.v4.runtime.*;

import java.io.IOException;
import java.util.Objects;

public class SyntaxParser {
    public void parse(String source) throws IOException {
        CharStream charStream = CharStreams.fromStream(
                Objects.requireNonNull(Thread.currentThread()
                        .getContextClassLoader()
                        .getResourceAsStream(source)));
        JerLexer lexer = new JerLexer(charStream);
        lexer.addErrorListener(new ErrorListener());
        JerParser parser = new JerParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());

        // try get the parsed object
        JerParser.CompilationUintContext a = parser.compilationUint();
    }
}
