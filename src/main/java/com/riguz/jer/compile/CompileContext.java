package com.riguz.jer.compile;

import com.riguz.jer.compile.def.Script;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class CompileContext {
    private final Path outputPath;
    private final List<Script> scripts;

    public CompileContext(Path outputPath, List<Script> scripts) {
        this.outputPath = outputPath;
        this.scripts = Collections.unmodifiableList(scripts);
    }

    public Path getOutputPath() {
        return outputPath;
    }

    public List<Script> getScripts() {
        return scripts;
    }
}
