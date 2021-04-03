package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Expression;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.Statement;
import com.riguz.jer.compile.def.expression.FunctionCall;
import com.riguz.jer.compile.def.expression.Literal;
import com.riguz.jer.compile.def.expression.VariableReference;
import com.riguz.jer.compile.def.statement.*;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

        ProcessStatement s1 = (ProcessStatement) statements.get(3);

        assertEquals("sayHello", s1.getName());
        assertEquals(0, s1.getArguments().size());
    }

    @Test
    public void parseSelectionStatement() {
        SelectionStatement s = (SelectionStatement) statements.get(4);

        FunctionCall c = (FunctionCall) s.getCondition();
        assertEquals("eq", c.getFunctionName());
        assertNull(s.getOpposite());
        assertEquals(new ProcessStatement("sayHello", Collections.emptyList()),
                s.getStatement());

        SelectionStatement s1 = (SelectionStatement) statements.get(5);

        FunctionCall c1 = (FunctionCall) s1.getCondition();
        assertEquals("eq", c1.getFunctionName());
        assertEquals(new ProcessStatement("print", Arrays.asList(new Literal("foo is 10"))),
                s1.getStatement());
    }

    @Test
    public void parseLoopStatement() {
        LoopStatement s = (LoopStatement) statements.get(6);

        FunctionCall c = (FunctionCall) s.getCondition();
        assertEquals(new FunctionCall(new VariableReference("foo", false),
                "gt",
                Collections.singletonList(new Literal("0"))), c);
        assertEquals(new AssignStatement("foo",
                        new FunctionCall(new VariableReference("foo", false),
                                "minus",
                                Collections.singletonList(new Literal("1")))),
                s.getStatement());
    }
}