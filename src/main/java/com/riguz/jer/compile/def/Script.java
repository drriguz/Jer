package com.riguz.jer.compile.def;

public class Script {
    private final String fileName;
    private final String packageName;

    public Script(String fileName, String packageName) {
        this.fileName = fileName;
        this.packageName = packageName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPackageName() {
        return packageName;
    }
}
