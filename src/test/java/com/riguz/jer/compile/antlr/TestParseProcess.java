package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Process;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestParseProcess {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));
    List<Process> processes;

    @Before
    public void setUp() throws ParseException {
        String source = "com/riguz/examples/process/EmptyProcesses.jer";
        Script parsed = parser.parse(source);
        processes = parsed.getProcesses();
    }

    @Test
    public void parseProcess() {
        assertEquals("empty", processes.get(0).getName());
        assertEquals("foo", processes.get(1).getName());
        assertEquals("bar", processes.get(2).getName());
        assertEquals(3, processes.size());
    }

    @Test
    public void parseProcessArguments() {
        assertEquals(0, processes.get(0).getFormalParameters().size());
        assertEquals(1, processes.get(1).getFormalParameters().size());
        assertEquals(2, processes.get(2).getFormalParameters().size());
    }
}