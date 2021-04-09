package com.riguz.jer.compile.asm;

import com.riguz.jer.compile.pipe.bytecode.CompiledClass;
import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.antlr.AntlrParser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.CompileException;
import com.riguz.jer.compile.exception.ParseException;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class AsmByteCodeTranslatorTest {
//    final ByteCodeTranslator translator = new AsmByteCodeTranslator();
//    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));
//
//    @Test
//    public void generateDefaultClassIfConstantFound() throws ParseException, CompileException {
//        String source = "com/riguz/examples/constant/PrimitiveTypes.jer";
//        Script parsed = parser.parse(source);
//        List<CompiledClass> compiled = translator.translate(parsed);
//
//        assertEquals(1, compiled.size());
//        assertEquals("com/riguz/examples/constant/PrimitiveTypes.class", compiled.get(0).getFileName());
//    }
//
//    @Test
//    public void generateDefaultClassIfProcessFound() throws ParseException, CompileException {
//        String source = "com/riguz/examples/process/EmptyProcesses.jer";
//        Script parsed = parser.parse(source);
//        List<CompiledClass> compiled = translator.translate(parsed);
//
//        assertEquals(1, compiled.size());
//        assertEquals("com/riguz/examples/process/EmptyProcesses.class", compiled.get(0).getFileName());
//    }
}