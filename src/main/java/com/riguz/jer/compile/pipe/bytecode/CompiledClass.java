package com.riguz.jer.compile.pipe.bytecode;

public class CompiledClass {
    private final String fileName;
    private final byte[] bytes;

    public CompiledClass(String fileName, byte[] bytes) {
        this.fileName = fileName;
        this.bytes = bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
