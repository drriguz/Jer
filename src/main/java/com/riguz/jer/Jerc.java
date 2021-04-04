package com.riguz.jer;

import com.riguz.jer.compile.Compiler;
import org.apache.commons.cli.*;

import java.nio.file.Paths;

public class Jerc {
    private static Options buildOptions() {
        Options options = new Options();
        Option baseDir = new Option("w", "base-dir", true, "package base directory");
        baseDir.setRequired(true);
        options.addOption(baseDir);
        return options;
    }

    /*
        test:
        -w /Users/pwc/Documents/PROJECTS/Jer/src/test/resources com/riguz/examples/HelloWorld.jer
     */
    public static void main(String[] args) {
        final CommandLine cmd;
        final Options options = buildOptions();
        try {
            CommandLineParser parser = new DefaultParser();
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            new HelpFormatter().printHelp("java com.riguz.jer.compile.Compiler <option> ...", options);
            return;
        }

        String baseDir = cmd.getOptionValue("base-dir");
        Compiler compiler = new Compiler(Paths.get(baseDir), cmd.getArgList());
        compiler.compile();
    }
}
