package com.riguz.jer.compile.pipe.bytecode;

public class CompiledClass {
    private final String classFullName;
    private final String fileName;
    private final byte[] bytes;

    public CompiledClass(String classFullName, byte[] bytes) {
        this.classFullName = classFullName;
        this.fileName = classFullName + ".class";
        this.bytes = bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
