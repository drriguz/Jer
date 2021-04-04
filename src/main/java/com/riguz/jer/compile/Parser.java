package com.riguz.jer.compile;

import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.ParseException;

public interface Parser {
    Script parse(String sourceFile) throws ParseException;
}
