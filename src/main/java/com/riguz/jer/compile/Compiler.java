package com.riguz.jer.compile;

import com.riguz.jer.compile.antlr.AntlrParser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.ParseException;
import com.riguz.jer.compile.pipe.bytecode.BytecodeTranslator;
import com.riguz.jer.compile.pipe.bytecode.CompiledClass;
import com.riguz.jer.compile.pipe.pre.ClassDefinition;
import com.riguz.jer.compile.pipe.pre.PreProcessor;
import com.riguz.jer.compile.pipe.validation.StaticChecker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Compiler {
    private final Path baseDir;
    private final List<String> files;
    private final Parser parser;

    private final PreProcessor preProcessor = new PreProcessor();
    private final StaticChecker staticChecker = new StaticChecker();
    private final BytecodeTranslator translator = new BytecodeTranslator();

    public Compiler(Path baseDir, List<String> files) {
        this.baseDir = baseDir;
        this.parser = new AntlrParser(baseDir);
        this.files = Collections.unmodifiableList(files);
    }

    public void compile() {
        List<Script> scripts = files.stream()
                .map(this::parse)
                .collect(Collectors.toList());

        List<ClassDefinition> classDefinitions = preProcessor.process(scripts);
        staticChecker.check(classDefinitions);
        List<CompiledClass> compiledClasses = translator.translate(classDefinitions);

        save(compiledClasses);
    }

    private void save(List<CompiledClass> compiledClasses) {
        compiledClasses.forEach(compiledClass -> {
            Path classPath = baseDir.resolve(compiledClass.getFileName());
            try {
                Files.write(classPath, compiledClass.getBytes());
                System.out.println("Generated: " + classPath);
            } catch (IOException e) {
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
