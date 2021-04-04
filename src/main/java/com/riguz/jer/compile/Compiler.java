package com.riguz.jer.compile;

import com.riguz.jer.compile.antlr.AntlrParser;
import com.riguz.jer.compile.def.Script;
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
                .map(f -> {
                    try {
                        return parser.parse(f);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
