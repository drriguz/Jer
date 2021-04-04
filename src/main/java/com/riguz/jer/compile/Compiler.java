package com.riguz.jer.compile;

import com.riguz.jer.compile.antlr.AntlrParser;
import com.riguz.jer.compile.asm.AsmByteCodeTranslator;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;
import com.riguz.jer.compile.exception.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
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
        CompileContext context = new CompileContext();
        addJvmDefaultImports(context);

        ByteCodeTranslator translator = new AsmByteCodeTranslator(context);
        List<CompiledClass> compiledClasses = scripts.stream()
                .map(script -> {
                    try {
                        return translator.translate(script);
                    } catch (CompileException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

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


    private void addJvmDefaultImports(CompileContext context) {
        context.addFullQualifiedType("Byte", "java/lang/Byte");
        context.addFullQualifiedType("Boolean", "java/lang/Boolean");
        context.addFullQualifiedType("Integer", "java/lang/Integer");
        context.addFullQualifiedType("Float", "java/lang/Float");
        context.addFullQualifiedType("Short", "java/lang/Short");
        context.addFullQualifiedType("Long", "java/lang/Long");
        context.addFullQualifiedType("Double", "java/lang/Double");
        context.addFullQualifiedType("Character", "java/lang/Character");
        context.addFullQualifiedType("System", "java/lang/System");
    }

    private Script parse(String file) {
        try {
            return parser.parse(file);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
