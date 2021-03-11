package com.riguz.jer.compile.def;

import java.util.Collections;
import java.util.List;

public class Script {
    private final String fileName;
    private final String packageName;
    private final List<String> importedTypes;

    public Script(String fileName,
                  String packageName,
                  List<String> importedTypes) {
        this.fileName = fileName;
        this.packageName = packageName;
        this.importedTypes = Collections.unmodifiableList(importedTypes);
    }

    public String getFileName() {
        return fileName;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<String> getImportedTypes() {
        return importedTypes;
    }
}
