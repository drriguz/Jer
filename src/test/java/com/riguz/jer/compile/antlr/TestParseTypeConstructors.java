package com.riguz.jer.compile.antlr;

import com.riguz.jer.compile.Parser;
import com.riguz.jer.compile.def.Block;
import com.riguz.jer.compile.def.Constructor;
import com.riguz.jer.compile.def.Script;
import com.riguz.jer.compile.def.Type;
import com.riguz.jer.compile.def.expression.Literal;
import com.riguz.jer.compile.def.statement.AssignStatement;
import com.riguz.jer.compile.exception.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestParseTypeConstructors {
    final Parser parser = new AntlrParser(Paths.get("src/test/resources"));
    List<Type> types;

    @Before
    public void setUp() throws ParseException {
        String source = "com/riguz/examples/type/Constructors.jer";
        Script parsed = parser.parse(source);
        types = parsed.getTypes();
    }

    @Test
    public void parseEmptyAbstract() {
        Type a = types.get(0);
        assertEquals(3, a.getConstructors().size());
        assertEquals(new Constructor(Collections.emptyList(),
                        new Block(Collections.singletonList(
                                new AssignStatement("age", new Literal("0"))))),
                a.getConstructors().get(0));
        assertEquals(2, a.getConstructors().get(1).getFormalParameters().size());
        assertEquals(3, a.getConstructors().get(2).getFormalParameters().size());
    }
}