package com.riguz.jer.compile.pipe.pre;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Map;

public abstract class ClassDefinition {
    protected final String className;
    protected final String packageName;
    protected final Map<String, String> importedClasses;

    public ClassDefinition(String className,
                           String packageName,
                           Map<String, String> importedClasses) {
        this.className = className;
        this.packageName = packageName;
        this.importedClasses = Collections.unmodifiableMap(importedClasses);
    }

    public String getFullName() {
        return StringUtils.isEmpty(packageName) ? className :
                packageName + '/' + className;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public Map<String, String> getImportedClasses() {
        return importedClasses;
    }
}
