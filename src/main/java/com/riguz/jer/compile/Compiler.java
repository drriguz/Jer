package com.riguz.jer.compile;

import com.riguz.jer.compile.antlr.AntlrParser;
import com.riguz.jer.compile.asm.AsmByteCodeTranslator;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;
import com.riguz.jer.compile.exception.ParseException;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Compiler {
    private final Path baseDir;
    private final List<String> files;
    private final Parser parser;

    public Compiler(Path baseDir, List<String> files) {
        this.baseDir = baseDir;
        this.parser = new AntlrParser(baseDir);
        this.files = Collections.unmodifiableList(files);
    }

    public void compile() {
        List<Script> scripts = files.stream()
                .map(this::parse)
                .collect(Collectors.toList());
        CompileContext context = new CompileContext(baseDir, scripts);
        ByteCodeTranslator translator = new AsmByteCodeTranslator();
        scripts.forEach(s -> {
            try {
                translator.translate(s);
            } catch (CompileException e) {
                e.printStackTrace();
            }
        });
    }

    private Script parse(String file) {
        try {
            return parser.parse(file);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
