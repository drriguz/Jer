package com.riguz.jer.compile.antlr;

import com.riguz.jer.antlr.generated.JerLexer;
import com.riguz.jer.antlr.generated.JerParser;
import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AntlrParser implements Parser {
    private final Path workingDirectory;

    public AntlrParser(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public AntlrParser() {
        this.workingDirectory = Paths.get(".");
    }

    @Override
    public Script parse(String sourceFile) {
        Path filePath = workingDirectory.resolve(sourceFile);
        String packageName = new File(sourceFile).getParentFile().getPath();
        try {
            File file = filePath.toFile();
            JerParser antlrParser = createAntlrParser(file);
            ScriptVisitor scriptVisitor = new ScriptVisitor(file.getName(), packageName);
            return scriptVisitor.visitCompilationUint(antlrParser.compilationUint());
        } catch (IOException e) {
            throw new CompileException("Parse error:" + e.getMessage());
        }
    }

    private JerParser createAntlrParser(File source) throws IOException {
        CharStream charStream = CharStreams.fromStream(
                new FileInputStream(source));
        JerLexer lexer = new JerLexer(charStream);
        lexer.addErrorListener(new ErrorListener());
        JerParser parser = new JerParser(new CommonTokenStream(lexer));
        parser.setErrorHandler(new BailErrorStrategy());

        return parser;
    }
}
