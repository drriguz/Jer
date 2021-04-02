package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.Statement;
import com.riguz.jer.compile.def.expression.FunctionCall;
import com.riguz.jer.compile.def.expression.Literal;
import com.riguz.jer.compile.def.statement.AssignStatement;
import com.riguz.jer.compile.def.statement.ProcessStatement;
import com.riguz.jer.compile.def.statement.VariableDeclaration;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestParseStatements {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));
    List<Statement> statements;

    @Before
    public void setUp() {
        String source = "com/riguz/examples/process/Statements.jer";
        Script parsed = parser.parse(source);
        statements = parsed.getProcesses().get(0).getBlock().getStatements();
    }

    @Test
    public void parseVariableDeclaration() {
        VariableDeclaration s = (VariableDeclaration) statements.get(0);

        assertEquals("foo", s.getVariableName());
        assertEquals("Integer", s.getType());
        assertEquals("10", ((Literal) s.getVariableInitializer().getValue()).getValue());
    }

    @Test
    public void parseAssignment() {
        AssignStatement s = (AssignStatement) statements.get(1);

        assertEquals("bar", s.getVariableName());
        FunctionCall v = (FunctionCall) s.getExpression();
        assertEquals("add", v.getFunctionName());
        assertEquals("20", ((Literal) v.getInstance()).getValue());
        assertEquals(1, v.getArguments().size());
        assertEquals("10", ((Literal) v.getArguments().get(0)).getValue());
    }

    @Test
    public void parseRunProcessStatement() {
        ProcessStatement s = (ProcessStatement) statements.get(2);

        assertEquals("sum", s.getName());
        assertEquals(3, s.getArguments().size());
        assertEquals("1", ((Literal) s.getArguments().get(0)).getValue());
    }
}