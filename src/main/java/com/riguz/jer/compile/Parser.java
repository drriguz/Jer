package com.riguz.jer.compile;

import com.riguz.jer.compile.def.Script;

public interface Parser {
    Script parse(String sourceFile);
}
