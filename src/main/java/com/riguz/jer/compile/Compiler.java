package com.riguz.jer.compile;

public class Compiler {
    private final String[] files;

    public Compiler(String... files) {
        this.files = files;
    }

    public void compile() {

    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java -c com.riguz.jer.compile.Compiler <file 1> ... <file n>");
            return;
        }
        Compiler compiler = new Compiler(args);
        compiler.compile();
    }
}
