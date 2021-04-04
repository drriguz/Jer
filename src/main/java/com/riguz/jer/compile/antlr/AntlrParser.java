package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerLexer;
import com.riguz.jer.antlr.generated.JerParser;
import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.ParseException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AntlrParser implements Parser {
    private final Path baseDirectory;

    public AntlrParser(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    public AntlrParser() {
        this.baseDirectory = Paths.get(".");
    }

    @Override
    public Script parse(String sourceFile) throws ParseException {
        Path filePath = baseDirectory.resolve(sourceFile);
        if (!Files.isRegularFile(filePath))
            throw new ParseException("File not exists or it's not a file:" + filePath);
        String packageName = new File(sourceFile).getParentFile().getPath();
        try {
            File file = filePath.toFile();
            JerParser antlrParser = createAntlrParser(file);
            ScriptVisitor scriptVisitor = new ScriptVisitor(file.getName(), packageName);
            return scriptVisitor.visitCompilationUint(antlrParser.compilationUint());
        } catch (IOException e) {
            throw new ParseException("Parse error:" + e.getMessage());
        }
    }

    private JerParser createAntlrParser(File source) throws IOException {
        CharStream charStream = CharStreams.fromStream(
                new FileInputStream(source));
        JerLexer lexer = new JerLexer(charStream);
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        JerParser parser = new JerParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);
        // parser.setErrorHandler(new BailErrorStrategy());

        return parser;
    }
}
